package pl.jsildatk.analyzer.dto;

import lombok.ToString;
import lombok.Value;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.parser.Unit;

@Value
@ToString
public class TelemetryData {
    
    Type type;
    
    Unit unit;
    
    double value;
    
}
