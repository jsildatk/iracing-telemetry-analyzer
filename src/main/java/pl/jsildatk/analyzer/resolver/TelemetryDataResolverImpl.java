package pl.jsildatk.analyzer.resolver;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.jsildatk.analyzer.dto.SingleTypeData;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryLap;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.util.TimeUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleFunction;

@Component
@Slf4j
public class TelemetryDataResolverImpl implements TelemetryDataResolver {
    
    private static final DoubleFunction<Double> METERS_TO_KILOMETERS = meters -> meters * 3600 / 1000;
    
    @Override
    public Map<Integer, Type> resolveHeader(String[] header) {
        final Stopwatch sw = Stopwatch.createStarted();
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
        
        final long timeElapsed = sw.elapsed(TimeUnit.MILLISECONDS);
        log.info("Time elapsed for resolving CSV header: {} milliseconds", timeElapsed);
        return indexToType;
    }
    
    @Override
    public List<TelemetryData> resolve(Map<Integer, Type> indexToType, String[] line) {
        final List<TelemetryData> data = Lists.newArrayList();
        for ( int i = 0; i < line.length; i++ ) {
            if ( indexToType.containsKey(i) ) {
                final Type type = indexToType.get(i);
                data.add(new TelemetryData(type, Double.parseDouble(line[i])));
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
            final double[] rpm = getMinAndMaxRpm(dataByLap);
            final int[] gear = getMinAndMaxGear(dataByLap);
            final double[] steeringWheelAngle = getMinAndMaxSteeringWheelAngle(dataByLap);
            final double[] speed = getMinAndMaxSpeed(dataByLap);
            result.add(new TelemetryLap(lap, getLapTime(dataByLap), gear[0], gear[1], rpm[0], rpm[1], steeringWheelAngle[0], steeringWheelAngle[1],
                    speed[0], speed[1], dataByLap));
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
                        result.add(Math.toDegrees(telemetryData.getValue()));
                    } else if ( telemetryDataType == Type.Speed ) { // convert speed to km/h from m/s
                        result.add(METERS_TO_KILOMETERS.apply(telemetryData.getValue()));
                    } else {
                        result.add(telemetryData.getValue());
                    }
                }
            }
        }
        log.debug("Created data for type: {}", type);
        return result;
    }
    
    private String getLapTime(List<SingleTypeData> data) {
        final List<Double> value = getValuesByType(data, Type.LapCurrentLapTime);
        final double lapTime = value.get(value.size() - 1);
        return TimeUtils.formatLapTime(lapTime);
    }
    
    private double[] getMinAndMaxSteeringWheelAngle(List<SingleTypeData> data) {
        return getMinAndMaxForDoubleType(data, Type.SteeringWheelAngle);
    }
    
    private int[] getMinAndMaxGear(List<SingleTypeData> data) {
        final IntSummaryStatistics summaryStatistics = getValuesByType(data, Type.Gear).stream()
                .mapToInt(Double::intValue)
                .summaryStatistics();
        return new int[]{ summaryStatistics.getMin(), summaryStatistics.getMax() };
    }
    
    private double[] getMinAndMaxRpm(List<SingleTypeData> data) {
        return getMinAndMaxForDoubleType(data, Type.RPM);
    }
    
    private double[] getMinAndMaxSpeed(List<SingleTypeData> data) {
        return getMinAndMaxForDoubleType(data, Type.Speed);
    }
    
    private double[] getMinAndMaxForDoubleType(List<SingleTypeData> data, Type type) {
        final DoubleSummaryStatistics summaryStatistics = getValuesByType(data, type).stream()
                .mapToDouble(value -> value)
                .summaryStatistics();
        return new double[]{ summaryStatistics.getMin(), summaryStatistics.getMax() };
    }
    
    @VisibleForTesting
    List<Double> getValuesByType(List<SingleTypeData> data, Type type) {
        for ( SingleTypeData singleTypeData : data ) {
            if ( singleTypeData.getType() == type ) {
                return singleTypeData.getValue();
            }
        }
        throw new IllegalArgumentException(String.format("Data for type: %s was not found", type));
    }
    
}
