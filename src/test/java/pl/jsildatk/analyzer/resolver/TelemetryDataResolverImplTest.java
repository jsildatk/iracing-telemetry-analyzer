package pl.jsildatk.analyzer.resolver;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.parser.Type;

import java.io.FileReader;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

public class TelemetryDataResolverImplTest {
    
    private TelemetryDataResolverImpl telemetryDataResolver;
    
    @BeforeEach
    public void setUp() {
        telemetryDataResolver = new TelemetryDataResolverImpl();
    }
    
    @Test
    public void testMapping() {
        assertThat(telemetryDataResolver.getMapping(), aMapWithSize(49));
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
    
    private String[] readFile(String path) throws Exception {
        CSVReader csvReader = new CSVReader(new FileReader(path));
        return csvReader.readAll()
                .get(0);
    }
    
}