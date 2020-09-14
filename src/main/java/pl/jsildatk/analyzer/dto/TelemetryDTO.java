package pl.jsildatk.analyzer.dto;

import lombok.Value;

import java.util.List;

@Value
public class TelemetryDTO {
    
    List<List<TelemetryData>> telemetryData;
    
    TelemetryInfo telemetryInfo;
    
}
