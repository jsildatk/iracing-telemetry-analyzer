package pl.jsildatk.analyzer.service;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import pl.jsildatk.analyzer.dto.TelemetryDTO;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryInfo;
import pl.jsildatk.analyzer.resolver.TelemetryDataResolver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class GuiServiceImpl implements GuiService {
    
    private final TelemetryDataResolver telemetryDataResolver;
    
    @Override
    public ModelMap createModel(TelemetryDTO telemetryDTO) {
        final Stopwatch sw = Stopwatch.createStarted();
        final ModelMap modelMap = new ModelMap();
        createTelemetryInfoModel(modelMap, telemetryDTO.getTelemetryInfo());
        createTelemetryLapModel(modelMap, telemetryDTO.getTelemetryData());
        createTelemetryColumns(modelMap);
        
        final long timeElapsed = sw.elapsed(TimeUnit.MILLISECONDS);
        log.info("Time elapsed for converting telemetry to model: {} milliseconds", timeElapsed);
        return modelMap;
    }
    
    private void createTelemetryInfoModel(ModelMap modelMap, TelemetryInfo telemetryInfo) {
        modelMap.put("title", createTitle(telemetryInfo));
        modelMap.put("subtitle", createSubtitle(telemetryInfo));
    }
    
    private void createTelemetryLapModel(ModelMap modelMap, List<List<TelemetryData>> telemetryData) {
        modelMap.put("laps", telemetryDataResolver.getLapsData(telemetryData));
    }
    
    private void createTelemetryColumns(ModelMap modelMap) {
        final Map<String, String> columns = ImmutableMap.<String, String> builder()
                .put("RPM", "RPM")
                .put("SteeringWheelAngle", "Steering Wheel Angle")
                .put("Gear", "Gear")
                .put("Speed", "Speed")
                .put("Inputs", "Inputs")
                .build();
        modelMap.put("columns", columns);
    }
    
    private String createTitle(TelemetryInfo telemetryInfo) {
        return telemetryInfo.getDriver() + " - " + telemetryInfo.getVehicle() + " - " + telemetryInfo.getVenue() + " - " + telemetryInfo.getSession();
    }
    
    private String createSubtitle(TelemetryInfo telemetryInfo) {
        return telemetryInfo.getSessionDate() + " - " + telemetryInfo.getSessionTime() + " - " + telemetryInfo.getSessionDuration() + " - " +
               telemetryInfo.getSampleRate();
    }
    
}
