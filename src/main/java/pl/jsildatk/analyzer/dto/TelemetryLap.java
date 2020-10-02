package pl.jsildatk.analyzer.dto;

import lombok.Value;

import java.util.List;

/**
 * Immutable class representing single lap from telemetry (created from thousands of ticks). Contains: <br>
 * <ol>
 *     <li>number of lap</li>
 *     <li>lap time as formatted string - m:ss.SSS</li>
 *     <li>minimum value for {@link pl.jsildatk.analyzer.parser.Type#Gear} as number</li>
 *     <li>maximum value for {@link pl.jsildatk.analyzer.parser.Type#Gear} as number</li>
 *     <li>minimum value for {@link pl.jsildatk.analyzer.parser.Type#RPM} as double</li>
 *     <li>maximum value for {@link pl.jsildatk.analyzer.parser.Type#RPM} as double</li>
 *     <li>minimum value for {@link pl.jsildatk.analyzer.parser.Type#SteeringWheelAngle} as degrees</li>
 *     <li>maximum value for {@link pl.jsildatk.analyzer.parser.Type#SteeringWheelAngle} as degrees</li>
 *     <li>minimum value for {@link pl.jsildatk.analyzer.parser.Type#Speed} as km/h</li>
 *     <li>maximum value for {@link pl.jsildatk.analyzer.parser.Type#Speed} as km/h</li>
 *     <li>list of data of each type {@link SingleTypeData}</li>
 * </ol>
 *
 * @author Jakub Sildatk
 * @since 1.0.0
 */
@Value
public class TelemetryLap {
    
    int number;
    
    String lapTime;
    
    int minGear;
    
    int maxGear;
    
    double minRpm;
    
    double maxRpm;
    
    double minSteeringAngle;
    
    double maxSteeringAngle;
    
    double minSpeed;
    
    double maxSpeed;
    
    List<SingleTypeData> data;
    
}
