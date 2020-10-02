package pl.jsildatk.analyzer.dto;

import lombok.ToString;
import lombok.Value;
import pl.jsildatk.analyzer.parser.Type;

/**
 * Immutable class representing single telemetry tick with {@link Type} and value represented as double.
 *
 * @author Jakub Sildatk
 * @since 1.0.0
 */
@Value
@ToString
public class TelemetryData {
    
    Type type;
    
    double value;
    
}
