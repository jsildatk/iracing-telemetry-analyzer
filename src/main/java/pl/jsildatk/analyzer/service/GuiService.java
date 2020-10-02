package pl.jsildatk.analyzer.service;

import org.springframework.ui.ModelMap;
import pl.jsildatk.analyzer.dto.TelemetryDTO;

/**
 * Interface for creating MVC {@link org.springframework.ui.Model}.
 *
 * @author Jakub Sildatk
 */
public interface GuiService {
    
    /**
     * Create MVC {@link org.springframework.ui.Model} using lap data from {@link pl.jsildatk.analyzer.resolver.TelemetryDataResolver}. <br>
     * It creates map with 4 keys: <br>
     * <ol>
     *     <li>title - containing driver's name, vehicle, track and session type</li>
     *     <li>subtitle - containing session date, session time, session duration and sample rate</li>
     *     <li>laps - list containing {@link pl.jsildatk.analyzer.dto.TelemetryLap} with all specific lap's data</li>
     *     <li>columns - map with {id, text} pair for filtering chart columns on frontend</li>
     * </ol>
     *
     * @param telemetryDTO {@link TelemetryDTO}
     * @return {@link ModelMap} containing above keys
     * @since 1.0.0
     */
    ModelMap createModel(TelemetryDTO telemetryDTO);
    
}
