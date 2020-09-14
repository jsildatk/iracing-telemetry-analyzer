package pl.jsildatk.analyzer.resolver;

import pl.jsildatk.analyzer.dto.TelemetryData;

import java.util.List;

public interface TelemetryDataResolver {
    
    List<TelemetryData> resolve(String[] line);
    
}
