package pl.jsildatk.analyzer.controller;

import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.service.TelemetryService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TelemetryController {
    
    private final TelemetryService telemetryService;
    
    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
    
    @PostMapping("/analyze")
    public String analyzeTelemetry(@RequestParam("telemetry") MultipartFile telemetry)
            throws IllegalArgumentException, IOException, CsvValidationException {
        telemetryService.createTelemetry(telemetry);
        return "index";
    }
    
}
