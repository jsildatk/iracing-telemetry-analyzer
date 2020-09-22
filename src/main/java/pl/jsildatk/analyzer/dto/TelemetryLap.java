package pl.jsildatk.analyzer.dto;

import lombok.Value;

import java.util.List;

/**
 * Immutable class representing single lap from telemetry (created from thousands of ticks). Contains:
 * 1) number of lap
 * 2) lap time as formatted string
 * 3) minimum {@link pl.jsildatk.analyzer.parser.Type.Gear} value
 * 4) maximum {@link pl.jsildatk.analyzer.parser.Type.Gear} value
 * 5) minimum {@link pl.jsildatk.analyzer.parser.Type.RPM} value
 * 6) maximum {@link pl.jsildatk.analyzer.parser.Type.RPM} value
 * 7) minimum {@link pl.jsildatk.analyzer.parser.Type.SteeringWheelAngle} value
 * 8) maximum {@link pl.jsildatk.analyzer.parser.Type.SteeringWheelAngle} value
 * 9) minimum {@link pl.jsildatk.analyzer.parser.Type.Speed} value as km/h
 * 10) maximum {@link pl.jsildatk.analyzer.parser.Type.Speed} value as km/h
 * 11) list of data of each type {@link SingleTypeData}
 *
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
