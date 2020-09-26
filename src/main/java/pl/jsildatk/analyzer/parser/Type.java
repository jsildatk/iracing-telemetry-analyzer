package pl.jsildatk.analyzer.parser;

/**
 * Mapping types from CSV file. Only those types will be parsed from CSV file.
 *
 * @since 1.0.0
 */
public enum Type {
    
    //    AirTemp,
//    TrackTemp,
//    LFtempL,
//    LFtempM,
//    LFtempR,
//    LFcoldPressure,
//    LFpressure,
//    LRtempL,
//    LRtempM,
//    LRtempR,
//    LRcoldPressure,
//    LRpressure,
//    RFtempL,
//    RFtempM,
//    RFtempR,
//    RFcoldPressure,
//    RFpressure,
//    RRtempL,
//    RRtempM,
//    RRtempR,
//    RRcoldPressure,
//    RRpressure,
//    LapDeltaToBestLap,
//    LapBestLapTime,
    LapCurrentLapTime,
    Lap,
    //    LapDist,
    Clutch,
    Brake,
    Throttle,
    Gear,
    //    FuelLevel,
//    FuelUsePerHour,
    RPM,
    Speed,
    SteeringWheelAngle,
    dcMGUKDeployMode, // only for F1 car (25/50/75)
    DRS_Status // only for F1 car (1/2/3)
    
}
