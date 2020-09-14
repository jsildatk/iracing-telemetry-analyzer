package pl.jsildatk.analyzer.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Entry {
    
    private Type type;
    
    private Unit unit;
    
    private double value;
    
}
