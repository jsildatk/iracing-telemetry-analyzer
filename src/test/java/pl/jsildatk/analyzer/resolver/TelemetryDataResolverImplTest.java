package pl.jsildatk.analyzer.resolver;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.opencsv.CSVReader;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryLap;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.parser.Unit;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

public class TelemetryDataResolverImplTest {
    
    private TelemetryDataResolverImpl telemetryDataResolver;
    
    @BeforeEach
    public void setUp() {
        telemetryDataResolver = new TelemetryDataResolverImpl();
    }
    
    @Test
    public void testMapping() {
        assertThat(telemetryDataResolver.getMapping(), aMapWithSize(51));
    }
    
    @Test
    public void testResolvingHeader() {
        // given
        final String[] header = new String[]{ "Throttle", "Brake", "Clutch" };
        
        // when
        final Map<Integer, Type> result = telemetryDataResolver.resolveHeader(header);
        
        // then
        assertThat(result, allOf(hasEntry(0, Type.Throttle), hasEntry(1, Type.Brake), hasEntry(2, Type.Clutch)));
    }
    
    @Test
    public void testResolvingData() throws Exception {
        // given
        final Map<Integer, Type> map = createIntegerToTypeMapping();
        final String[] fileContent = readFile("src/test/resources/resolving_data.csv");
        
        // when
        final List<TelemetryData> result = telemetryDataResolver.resolve(map, fileContent);
        
        // then
        assertThat(result, hasSize(Type.values().length));
    }
    
    @Test
    public void testResolvingLapsData() {
        // given
        final TelemetryData brakeData1 = new TelemetryData(Type.Brake, Unit.PERCENT, 0.0);
        final TelemetryData brakeData2 = new TelemetryData(Type.Brake, Unit.PERCENT, 1.0);
        final TelemetryData clutchData = new TelemetryData(Type.Clutch, Unit.PERCENT, 34.2);
        
        // when
        final List<TelemetryLap> result = telemetryDataResolver.getLapsData(createTelemetryData(brakeData1, brakeData2, clutchData));
        
        // then
        assertThat(result, is(notNullValue()));
    }
    
    private Map<Integer, Type> createIntegerToTypeMapping() {
        final Map<Integer, Type> map = Maps.newHashMap();
        int i = 0;
        for ( Type type : Type.values() ) {
            map.put(i, type);
            i++;
        }
        return map;
    }
    
    private List<List<TelemetryData>> createTelemetryData(TelemetryData... data) {
        return ImmutableList.of(Lists.newArrayList(data));
    }
    
    private String[] readFile(String path) throws Exception {
        CSVReader csvReader = new CSVReader(new FileReader(path));
        return csvReader.readAll()
                .get(0);
    }
    
}