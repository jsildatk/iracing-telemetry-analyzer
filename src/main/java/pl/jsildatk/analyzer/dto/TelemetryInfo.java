package pl.jsildatk.analyzer.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@EqualsAndHashCode
public class TelemetryInfo {
    
    String driver;
    
    String vehicle;
    
    String venue;
    
    String session;
    
    String sessionDate;
    
    String sessionTime;
    
    String sampleRate;
    
    String sessionDuration;
    
}
