package pl.jsildatk.analyzer.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryInfo;
import pl.jsildatk.analyzer.resolver.TelemetryDataResolver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelemetryParserImpl implements TelemetryParser {
    
    private static final int TELEMETRY_INFO_LINES = 8;
    
    private final TelemetryDataResolver telemetryDataResolver;
    
    @Override
    public TelemetryInfo parseTelemetryInfo(InputStreamReader inputStreamReader) throws IOException {
        final CSVReader csvReader = new CSVReader(inputStreamReader);
        final List<String[]> lines = new ArrayList<>(8);
        
        try {
            for ( int i = 0; i < TELEMETRY_INFO_LINES; i++ ) {
                lines.add(csvReader.readNext());
            }
        } catch ( CsvValidationException e ) {
            log.warn(Arrays.toString(e.getStackTrace()));
        } finally {
            csvReader.close();
        }
        
        final TelemetryInfo telemetryInfo =
                new TelemetryInfo(lines.get(0)[1], lines.get(1)[1], lines.get(2)[1], lines.get(3)[1], lines.get(4)[1], lines.get(5)[1],
                        createSampleRate(lines.get(6)), createSessionTime(lines.get(7)));
        log.info("Parsed telemetry info: {}", telemetryInfo);
        return telemetryInfo;
    }
    
    @Override
    public List<List<TelemetryData>> parseTelemetryData(InputStreamReader inputStreamReader) throws IOException {
        final CSVReader csvReader = new CSVReader(inputStreamReader);
        csvReader.skip(TELEMETRY_INFO_LINES + 4);
        final List<List<TelemetryData>> telemetryData = new ArrayList<>();
        
        String[] line;
        try {
            while ( (line = csvReader.readNext()) != null ) {
                telemetryData.add(telemetryDataResolver.resolve(line));
            }
        } catch ( CsvValidationException e ) {
            log.warn(Arrays.toString(e.getStackTrace()));
        } finally {
            csvReader.close();
        }
        
        log.info("Parsed telemetry data - size: {}", telemetryData.size());
        return telemetryData;
    }
    
    private String createSessionTime(String[] line) {
        return Math.round(Double.parseDouble(line[1]) / 60 * 100.0) / 100.0 + " min";
    }
    
    private String createSampleRate(String[] line) {
        return line[1] + " " + line[2];
    }
    
}
