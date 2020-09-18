package pl.jsildatk.analyzer.dto;

import lombok.ToString;
import lombok.Value;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.parser.Unit;

/**
 * Immutable class representing single telemetry tick with {@link Type}, {@link Unit} and value represented as double value.
 *
 * @since 1.0.0
 */
@Value
@ToString
public class TelemetryData {
    
    Type type;
    
    Unit unit;
    
    double value;
    
}
