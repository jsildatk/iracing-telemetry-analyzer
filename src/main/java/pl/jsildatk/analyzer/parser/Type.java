package pl.jsildatk.analyzer.parser;

/**
 * Mapping types from CSV file. Only those types will be parsed from CSV file.
 *
 * @since 1.0.0
 */
public enum Type {
    
    //    AirDensity("Air density (kg/m^3)"),
//    AirTemp("Air temperature (Celsius)"),
//    FogLevel("Fog level (%)"),
//    RelativeHumidity("Relative humidity (%)"),
//    TrackTemp("Track temperature (Celsius)"),
//    LFwearL("Left front tyre wear - left side (%)"),
//    LFwearM("Left front tyre wear - middle (%)"),
//    LFwearR("Left front tyre wear - right side (%)"),
//    LFtempL("Left front temperature - left side (%)"),
//    LFtempM("Left front temperature - middle (%)"),
//    LFtempR("Left front temperature - right side (%)"),
//    LFcoldPressure("Left front cold pressure (kPa)"),
//    LFpressure("Left front pressure (kPa)"),
//    LRwearL("Left rear tyre wear - left side (%)"),
//    LRwearM("Left rear tyre wear - middle (%)"),
//    LRwearR("Left rear tyre wear - right side (%)"),
//    LRtempL("Left rear temperature - left side (%)"),
//    LRtempM("Left rear temperature - middle (%)"),
//    LRtempR("Left rear temperature - right side (%)"),
//    LRcoldPressure("Left rear cold pressure (kPa)"),
//    LRpressure("Left rear pressure (kPa)"),
//    RFwearL("Right front tyre wear - left side (%)"),
//    RFwearM("Right front tyre wear - middle (%)"),
//    RFwearR("Right front tyre wear - right side (%)"),
//    RFtempL("Right front temperature - left side (%)"),
//    RFtempM("Right front temperature - middle (%)"),
//    RFtempR("Right front temperature - right side (%)"),
//    RFcoldPressure("Right front cold pressure (kPa)"),
//    RFpressure("Right front pressure (kPa)"),
//    RRwearL("Right rear tyre wear - left side (%)"),
//    RRwearM("Right rear tyre wear - middle (%)"),
//    RRwearR("Right rear tyre wear - right side (%)"),
//    RRtempL("Right rear tyre wear - left side (%)"),
//    RRtempM("Right rear tyre wear - middle (%)"),
//    RRtempR("Right rear tyre wear - right side (%)"),
//    RRcoldPressure("Right rear cold pressure (kPa)"),
//    RRpressure("Right rear pressure (kPa)"),
//    LapDeltaToBestLap("Delta to best lap (s)"),
//    LapBestLapTime("Best lap time (m)"),
    LapCurrentLapTime("Current lap time (S)"),
    Lap("Lap number (int)"),
    //    LapDist("Lap distance (m)"),
    Clutch("Clutch force (%)"),
    Brake("Brake force (%)"),
    Throttle("Throttle force (%)"),
    Gear("Current gear (number)"),
    //    FuelLevel("Actual fuel level (l)"),
//    FuelUsePerHour("Fuel use per hour (kg/h)"),
    RPM("Rounds per minute"),
    Speed("Actual speed (km/h)"),
    SteeringWheelAngle("Actual steering wheel angle (radians)");
    
    private final String description;
    
    Type(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
}
