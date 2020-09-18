package pl.jsildatk.analyzer.service;

import org.springframework.ui.ModelMap;
import pl.jsildatk.analyzer.dto.TelemetryDTO;

public interface GuiService {
    
    ModelMap createModel(TelemetryDTO telemetryDTO);
    
}
