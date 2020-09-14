package pl.jsildatk.analyzer.resolver;

import pl.jsildatk.analyzer.dto.TelemetryData;

import java.util.List;

public interface TelemetryDataResolver {
    
    /**
     * Resolve single line from csv file as TelemetryData object
     *
     * @param line from csv file
     * @return list of telemetry data
     * @since 1.0.0
     */
    List<TelemetryData> resolve(String[] line);
    
}
