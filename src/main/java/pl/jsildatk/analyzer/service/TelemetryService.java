package pl.jsildatk.analyzer.service;

import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryDTO;

import java.io.IOException;

public interface TelemetryService {
    
    TelemetryDTO createTelemetry(MultipartFile telemetry) throws IOException;
    
}
