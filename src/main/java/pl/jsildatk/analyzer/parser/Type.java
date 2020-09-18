package pl.jsildatk.analyzer.parser;

/**
 * Mapping types from CSV file
 *
 * @since 1.0.0
 */
public enum Type {
    
    AirDensity("Air density (kg/m^3)", 1),
    AirTemp("Air temperature (Celsius)", 0),
    FogLevel("Fog level (%)", 28),
    RelativeHumidity("Relative humidity (%)", 191),
    TrackTemp("Track temperature (Celsius)", 216),
    LFwearL("Left front tyre wear - left side (%)", 3),
    LFwearM("Left front tyre wear - middle (%)", 63),
    LFwearR("Left front tyre wear - right side (%)", 64),
    LFtempL("Left front temperature - left side (%)", 14),
    LFtempM("Left front temperature - middle (%)", 5),
    LFtempR("Left front temperature - right side (%)", 61),
    LFcoldPressure("Left front cold pressure (kPa)", 55),
    LFpressure("Left front pressure (kPa)", 6),
    LRwearL("Left rear tyre wear - left side (%)", 40),
    LRwearM("Left rear tyre wear - middle (%)", 48),
    LRwearR("Left rear tyre wear - right side (%)", 77),
    LRtempL("Left rear temperature - left side (%)", 75),
    LRtempM("Left rear temperature - middle (%)", 9),
    LRtempR("Left rear temperature - right side (%)", 20),
    LRcoldPressure("Left rear cold pressure (kPa)", 70),
    LRpressure("Left rear pressure (kPa)", 41),
    RFwearL("Right front tyre wear - left side (%)", 168),
    RFwearM("Right front tyre wear - middle (%)", 169),
    RFwearR("Right front tyre wear - right side (%)", 170),
    RFtempL("Right front temperature - left side (%)", 165),
    RFtempM("Right front temperature - middle (%)", 166),
    RFtempR("Right front temperature - right side (%)", 167),
    RFcoldPressure("Right front cold pressure (kPa)", 42),
    RFpressure("Right front pressure (kPa)", 159),
    RRwearL("Right rear tyre wear - left side (%)", 185),
    RRwearM("Right rear tyre wear - middle (%)", 187),
    RRwearR("Right rear tyre wear - right side (%)", 188),
    RRtempL("Right rear tyre wear - left side (%)", 183),
    RRtempM("Right rear tyre wear - middle (%)", 113),
    RRtempR("Right rear tyre wear - right side (%)", 184),
    RRcoldPressure("Right rear cold pressure (kPa)", 44),
    RRpressure("Right rear pressure (kPa)", 175),
    LapDeltaToBestLap("Delta to best lap (s)", 8),
    LapBestLapTime("Best lap time (m)", 78),
    Lap("Lap number (int)", 22),
    LapDist("Lap distance (m)", 88),
    Clutch("Clutch force (%)", 10),
    Brake("Brake force (%)", 11),
    Throttle("Throttle force (%)", 210),
    Gear("Current gear (number)", 34),
    FuelLevel("Actual fuel level (l)", 39),
    FuelUsePerHour("Fuel use per hour (kg/h)", 47),
    RPM("Rounds per minute", 171),
    Speed("Actual speed (km/h)", 178),
    SteeringWheelAngle("Actual steering wheel angle (radians)", 206);
    
    private final String description;
    
    private final int index;
    
    Type(String description, int index) {
        this.description = description;
        this.index = index;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getIndex() {
        return index;
    }
    
    public static Type resolveByIndex(int index) {
        for ( Type type : values() ) {
            if ( type.getIndex() == index ) {
                return type;
            }
        }
        return null;
    }
    
}
