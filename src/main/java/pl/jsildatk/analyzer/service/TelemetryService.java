package pl.jsildatk.analyzer.service;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryDTO;

import java.io.IOException;

/**
 * Interface for creating complete {@link TelemetryDTO} containing all required data from CSV file.
 *
 * @author Jakub Sildatk
 */
public interface TelemetryService {
    
    /**
     * Create telemetry based on uploaded CSV file from web.
     *
     * @param telemetry file containing telemetry - must be in CSV format
     * @return telemetryDTO containing {@link pl.jsildatk.analyzer.dto.TelemetryInfo} and {@link pl.jsildatk.analyzer.dto.TelemetryData}
     * @throws IOException            if file was not found
     * @throws CsvValidationException if csv file is in invalid format
     * @since 1.0.0
     */
    TelemetryDTO createTelemetry(MultipartFile telemetry) throws IOException, CsvValidationException;
    
}
