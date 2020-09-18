package pl.jsildatk.analyzer.resolver;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.jsildatk.analyzer.dto.SingleTypeData;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryLap;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.parser.Unit;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class TelemetryDataResolverImpl implements TelemetryDataResolver {
    
    private static final String MAPPING_DATA_PATH = "src/main/resources/mapping.csv";
    
    private final Map<Type, Unit> mapping = createMappingFromCsv();
    
    @Override
    public Map<Integer, Type> resolveHeader(String[] header) {
        final Map<Integer, Type> indexToType = Maps.newHashMap();
        for ( Type type : Type.values() ) {
            for ( int i = 0; i < header.length; i++ ) {
                if ( type.name()
                        .equals(header[i]) ) {
                    indexToType.put(i, type);
                    break;
                }
            }
        }
        return indexToType;
    }
    
    @Override
    public List<TelemetryData> resolve(Map<Integer, Type> indexToType, String[] line) {
        final List<TelemetryData> data = Lists.newArrayList();
        for ( int i = 0; i < line.length; i++ ) {
            if ( indexToType.containsKey(i) ) {
                final Type type = indexToType.get(i);
                data.add(new TelemetryData(type, mapping.get(type), Double.parseDouble(line[i])));
            }
        }
        return data;
    }
    
    @Override
    public List<TelemetryLap> getLapsData(List<List<TelemetryData>> data) {
        final Stopwatch sw = Stopwatch.createStarted();
        final Set<Integer> laps = getLaps(data);
        final List<TelemetryLap> result = Lists.newArrayList();
        for ( Integer lap : laps ) {
            final List<SingleTypeData> dataByLap = getDataByLap(data, lap);
            result.add(new TelemetryLap(lap, getLapTime(dataByLap), dataByLap));
        }
        
        final long timeElapsed = sw.elapsed(TimeUnit.MILLISECONDS);
        log.info("Time elapsed for resolving lap data: {} milliseconds", timeElapsed);
        return result;
    }
    
    private Set<Integer> getLaps(List<List<TelemetryData>> data) {
        Set<Integer> result = Sets.newLinkedHashSet();
        for ( List<TelemetryData> datum : data ) {
            for ( TelemetryData telemetryData : datum ) {
                if ( telemetryData.getType() == Type.Lap ) {
                    result.add((int) Math.round(telemetryData.getValue()));
                }
            }
        }
        return result;
    }
    
    private List<SingleTypeData> getDataByLap(List<List<TelemetryData>> data, int lap) {
        final List<List<TelemetryData>> telemetryForLap = Lists.newArrayList();
        final List<SingleTypeData> result = Lists.newArrayList();
        for ( List<TelemetryData> datum : data ) {
            for ( TelemetryData telemetryData : datum ) {
                if ( telemetryData.getType() == Type.Lap && telemetryData.getValue() == lap ) {
                    telemetryForLap.add(datum);
                }
            }
        }
        for ( Type type : Type.values() ) {
            result.add(new SingleTypeData(type, getDataByType(telemetryForLap, type)));
        }
        return result;
    }
    
    private List<Double> getDataByType(List<List<TelemetryData>> data, Type type) {
        final List<Double> result = Lists.newArrayList();
        for ( List<TelemetryData> datum : data ) {
            for ( TelemetryData telemetryData : datum ) {
                final Type telemetryDataType = telemetryData.getType();
                if ( telemetryDataType == type ) {
                    if ( telemetryDataType == Type.Clutch ) { // clutch's value is reversed for some reason
                        result.add(100.0 - telemetryData.getValue());
                    } else if ( telemetryDataType == Type.SteeringWheelAngle ) { // convert steering wheel angle from radians to degree
                        result.add(Math.toRadians(telemetryData.getValue()));
                    } else {
                        result.add(telemetryData.getValue());
                    }
                }
            }
        }
        log.debug("Created data for type: {}", type);
        return result;
    }
    
    private double getLapTime(List<SingleTypeData> data) {
        final List<Double> value = data.get(39)
                .getValue();
        return value.get(value.size() - 1);
    }
    
    private Map<Type, Unit> createMappingFromCsv() {
        final Map<Type, Unit> data = Maps.newHashMap();
        try {
            final CSVReader csvReader = new CSVReader(new FileReader(MAPPING_DATA_PATH));
            for ( String[] line : csvReader.readAll() ) {
                data.put(Type.valueOf(line[0]), Unit.resolveByLabel(line[1].trim()));
            }
        } catch ( IOException | CsvException e ) {
            log.warn(Arrays.toString(e.getStackTrace()));
        }
        
        return data;
    }
    
    @VisibleForTesting
    Map<Type, Unit> getMapping() {
        return mapping;
    }
    
}
