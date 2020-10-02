package pl.jsildatk.analyzer.dto;

import lombok.Value;
import pl.jsildatk.analyzer.parser.Type;

import java.util.List;

/**
 * Immutable class representing type with all corresponding data for lap. Used for getting all related data to lap.
 *
 * @author Jakub Sildatk
 * @see TelemetryLap
 * @see Type
 * @since 1.0.0
 */
@Value
public class SingleTypeData {
    
    Type type;
    
    List<Double> value;
    
}
