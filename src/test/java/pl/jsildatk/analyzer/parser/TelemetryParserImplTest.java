package pl.jsildatk.analyzer.parser;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryInfo;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TelemetryParserImplTest {
    
    private TelemetryParser telemetryParser;
    
    @BeforeEach
    public void setUp() {
        telemetryParser = new TelemetryParserImpl();
    }
    
    @Test
    public void testParsingTelemetryInfo() throws Exception {
        // given
        final MultipartFile file =
                new MockMultipartFile("test_parsing_telemetry_info.csv", createFileContent("src/test/resources/test_parsing_telemetry_info.csv"));
        
        // when
        final TelemetryInfo result = telemetryParser.parseTelemetryInfo(file);
        
        // then
        final TelemetryInfo expected =
                new TelemetryInfo("Test Driver", "dallarap217", "imola gp", "Test", "12/09/2020", "21:27:07", "60 Hz", "20.5333 s");
        assertThat(result, is(expected));
    }
    
    private byte[] createFileContent(String path) throws Exception {
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