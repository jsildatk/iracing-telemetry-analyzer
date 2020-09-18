//package pl.jsildatk.analyzer.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//import pl.jsildatk.analyzer.dto.TelemetryDTO;
//import pl.jsildatk.analyzer.parser.TelemetryParser;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static pl.jsildatk.analyzer.parser.TelemetryDataParserImplTest.createFileContent;
//
//public class TelemetryServiceImplTest {
//
//    private TelemetryService telemetryService;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        TelemetryParser telemetryParser = mock(TelemetryParser.class);
//        telemetryService = new TelemetryServiceImpl(telemetryParser);
//        when(telemetryParser.parseTelemetryInfo(Mockito.any())).thenReturn(null);
//        when(telemetryParser.parseTelemetryData(Mockito.any())).thenReturn(null);
//    }
//
//    @Test
//    public void testParsingTelemetryFile() throws Exception {
//        // given
//        final MultipartFile file = new MockMultipartFile("parsing_telemetry_data.csv", "parsing_telemetry_data.csv", null,
//                createFileContent("src/test/resources/parsing_telemetry.csv"));
//
//        // when
//        final TelemetryDTO result = telemetryService.createTelemetry(file);
//
//        // then
//        final TelemetryDTO expected = new TelemetryDTO(null, null);
//        assertThat(result, is(expected));
//    }
//
//}