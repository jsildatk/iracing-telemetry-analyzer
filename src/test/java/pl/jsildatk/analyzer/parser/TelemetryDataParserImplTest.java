package pl.jsildatk.analyzer.parser;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryInfo;
import pl.jsildatk.analyzer.resolver.TelemetryDataResolver;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TelemetryDataParserImplTest {
    
    private TelemetryParser telemetryParser;
    
    @BeforeEach
    public void setUp() {
        TelemetryDataResolver telemetryDataResolver = mock(TelemetryDataResolver.class);
        when(telemetryDataResolver.resolve(Mockito.any(String[].class))).thenReturn(
                Collections.singletonList(new TelemetryData(Type.AirDensity, Unit.BAR, 2.3)));
        telemetryParser = new TelemetryParserImpl(telemetryDataResolver);
    }
    
    @Test
    public void testParsingTelemetryInfo() throws Exception {
        // given
        final MultipartFile file =
                new MockMultipartFile("parsing_telemetry.csv", createFileContent("src/test/resources/parsing_telemetry.csv"));
        
        // when
        final TelemetryInfo result = telemetryParser.parseTelemetryInfo(new InputStreamReader(file.getInputStream()));
        
        // then
        final TelemetryInfo expected =
                new TelemetryInfo("Test Driver", "dallarap217", "imola gp", "Test", "12/09/2020", "21:27:07", "60 Hz", "0.34 min");
        assertThat(result, is(expected));
    }
    
    @Test
    public void testParsingTelemetryData() throws Exception {
        // given
        final MultipartFile file =
                new MockMultipartFile("parsing_telemetry_data.csv", createFileContent("src/test/resources/parsing_telemetry.csv"));
        
        // when
        final List<List<TelemetryData>> result = telemetryParser.parseTelemetryData(new InputStreamReader(file.getInputStream()));
        
        // then
        assertThat(result, hasSize(4));
        assertThat(result.get(0), hasSize(1));
    }
    
    public static byte[] createFileContent(String path) throws Exception {
        CSVReader csvReader = new CSVReader(new FileReader(path));
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        for ( String[] lines : csvReader.readAll() ) {
            for ( String line : lines ) {
                arrayOutputStream.write(line.getBytes());
                arrayOutputStream.write(",".getBytes());
            }
            arrayOutputStream.write("\n".getBytes());
        }
        return arrayOutputStream.toByteArray();
    }
    
}