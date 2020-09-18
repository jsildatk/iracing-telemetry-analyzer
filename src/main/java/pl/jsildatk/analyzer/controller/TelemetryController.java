package pl.jsildatk.analyzer.controller;

import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.service.GuiService;
import pl.jsildatk.analyzer.service.TelemetryService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TelemetryController {
    
    private final TelemetryService telemetryService;
    
    private final GuiService guiService;
    
    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
    
    @PostMapping("/analyze")
    public String analyzeTelemetry(@RequestParam("telemetry") MultipartFile telemetry, Model model)
            throws IllegalArgumentException, IOException, CsvValidationException {
        model.mergeAttributes(guiService.createModel(telemetryService.createTelemetry(telemetry)));
        return "telemetry";
    }
    
}
