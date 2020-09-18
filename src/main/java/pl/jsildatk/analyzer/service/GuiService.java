package pl.jsildatk.analyzer.service;

import org.springframework.ui.ModelMap;
import pl.jsildatk.analyzer.dto.TelemetryDTO;

public interface GuiService {
    
    /**
     * Create MVC {@link org.springframework.ui.Model} using lap data from {@link pl.jsildatk.analyzer.resolver.TelemetryDataResolver}. Used on GUI.
     * It creates map with 3 keys:
     * 1) title - containing driver's name, vehicle, track and session type
     * 2) subtitle - containing session date, session time, session duration and sample rate
     * 3) laps - list containing {@link pl.jsildatk.analyzer.dto.TelemetryLap} with all specific lap's data
     *
     * @param telemetryDTO {@link TelemetryDTO}
     * @return {@link ModelMap} containing above keys
     * @since 1.0.0
     */
    ModelMap createModel(TelemetryDTO telemetryDTO);
    
}
