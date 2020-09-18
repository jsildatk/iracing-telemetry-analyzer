package pl.jsildatk.analyzer.resolver;

import com.google.common.collect.ImmutableList;
import com.opencsv.CSVReader;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.parser.Unit;

import java.io.FileReader;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class TelemetryDataResolverImplTest {
    
    private TelemetryDataResolverImpl telemetryDataResolver;
    
    @BeforeEach
    public void setUp() {
        telemetryDataResolver = new TelemetryDataResolverImpl();
    }
    
    @Test
    public void testMapping() {
        assertThat(telemetryDataResolver.getMapping(), aMapWithSize(50));
    }
    
    @Test
    public void testResolvingData() throws Exception {
        // given
        final String[] fileContent = readFile("src/test/resources/resolving_data.csv");
        
        // when
        final List<TelemetryData> result = telemetryDataResolver.resolve(fileContent);
        
        // then
        assertThat(result, hasSize(Type.values().length));
    }
    
    @Test
    public void testResolvingByType() {
        // given
        final TelemetryData brakeData1 = new TelemetryData(Type.Brake, Unit.PERCENT, 0.0);
        final TelemetryData brakeData2 = new TelemetryData(Type.Brake, Unit.PERCENT, 1.0);
        final TelemetryData clutchData = new TelemetryData(Type.Clutch, Unit.PERCENT, 34.2);
        
        // when
        final List<Double> result = telemetryDataResolver.getDataByType(createTelemetryData(brakeData1, brakeData2, clutchData), Type.Brake);
        
        // then
        assertThat(result, containsInAnyOrder(brakeData1.getValue(), brakeData2.getValue()));
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