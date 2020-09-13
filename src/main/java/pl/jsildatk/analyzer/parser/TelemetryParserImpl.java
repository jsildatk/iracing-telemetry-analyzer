package pl.jsildatk.analyzer.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryInfo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TelemetryParserImpl implements TelemetryParser {
    
    private static final int TELEMETRY_INFO_LINES = 8;
    
    @Override
    public TelemetryInfo parseTelemetryInfo(MultipartFile telemetry) throws IOException, CsvValidationException {
        final CSVReader csvReader = new CSVReader(new InputStreamReader(telemetry.getInputStream()));
        final List<String[]> lines = new ArrayList<>(8);
        for ( int i = 0; i < TELEMETRY_INFO_LINES; i++ ) {
            lines.add(csvReader.readNext());
        }
        return new TelemetryInfo(lines.get(0)[1], lines.get(1)[1], lines.get(2)[1], lines.get(3)[1], lines.get(4)[1], lines.get(5)[1],
                createTwoDataInfo(lines.get(6)), createTwoDataInfo(lines.get(7)));
    }
    
    private String createTwoDataInfo(String[] line) {
        return line[1] + " " + line[2];
    }
    
}
