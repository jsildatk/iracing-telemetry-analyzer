package pl.jsildatk.analyzer.dto;

import lombok.Value;

import java.util.List;

/**
 * Immutable class representing single lap from telemetry (created from thousands of ticks). Contains number of lap, lap time and list of data of each type
 * {@link SingleTypeData}
 *
 * @since 1.0.0
 */
@Value
public class TelemetryLap {
    
    int number;
    
    double lapTime;
    
    List<SingleTypeData> data;
    
}
