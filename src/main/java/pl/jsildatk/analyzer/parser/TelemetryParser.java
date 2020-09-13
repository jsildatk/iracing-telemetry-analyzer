package pl.jsildatk.analyzer.parser;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryInfo;

import java.io.IOException;

public interface TelemetryParser {
    
    /**
     * Parse first 8 lines of telemetry files to TelemetryInfo object
     *
     * @param telemetry file with telemetry data
     * @return TelemetryInfo object
     * @throws IOException            if file was not found
     * @throws CsvValidationException if csv is in invalid format
     * @since 1.0.0
     */
    TelemetryInfo parseTelemetryInfo(MultipartFile telemetry) throws IOException, CsvValidationException;
    
}
