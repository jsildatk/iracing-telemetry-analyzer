package pl.jsildatk.analyzer.service;

import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryDTO;
import pl.jsildatk.analyzer.parser.TelemetryParser;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelemetryServiceImpl implements TelemetryService {
    
    private final TelemetryParser telemetryParser;
    
    @Override
    public TelemetryDTO createTelemetry(MultipartFile telemetry) throws IOException, CsvValidationException {
        log.info("Creating telemetry for file: {}", telemetry.getOriginalFilename());
        return new TelemetryDTO(null, telemetryParser.parseTelemetryInfo(telemetry));
    }
    
}
