package pl.jsildatk.analyzer.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import pl.jsildatk.analyzer.parser.CarType;

/**
 * Immutable class representing telemetry info from first 8 lines of CSV file. All data is used only for creating title and subtitle for GUI charts.
 *
 * @since 1.0.0
 */
@Value
@ToString
@EqualsAndHashCode
public class TelemetryInfo {
    
    String driver;
    
    CarType vehicle;
    
    String venue;
    
    String session;
    
    String sessionDate;
    
    String sessionTime;
    
    String sampleRate;
    
    String sessionDuration;
    
}
