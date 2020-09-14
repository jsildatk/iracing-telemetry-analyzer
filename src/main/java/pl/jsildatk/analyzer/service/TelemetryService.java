package pl.jsildatk.analyzer.service;

import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryDTO;

import java.io.IOException;

public interface TelemetryService {
    
    /**
     * Create telemetry based on uploaded csv file from web
     *
     * @param telemetry file containing telemetry
     * @return telemetryDTO containing {@link pl.jsildatk.analyzer.dto.TelemetryInfo} and {@link pl.jsildatk.analyzer.dto.TelemetryData}
     * @throws IOException if file was not found
     * @since 1.0.0
     */
    TelemetryDTO createTelemetry(MultipartFile telemetry) throws IOException;
    
}
