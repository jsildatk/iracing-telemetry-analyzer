package pl.jsildatk.analyzer.dto;

import lombok.Value;

import java.util.List;

/**
 * Immutable DTO containing all {@link TelemetryData} and {@link TelemetryInfo}from parser. Used by {@link pl.jsildatk.analyzer.service.TelemetryService}.
 *
 * @since 1.0.0
 */
@Value
public class TelemetryDTO {
    
    List<List<TelemetryData>> telemetryData;
    
    TelemetryInfo telemetryInfo;
    
}
