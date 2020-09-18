package pl.jsildatk.analyzer.parser;

/**
 * Mapping units from CSV file. Containing only required units.
 *
 * @since 1.0.0
 */
public enum Unit {
    
    BOOLEAN("boolean"), // for best lap
    INT("int"), // for gear
    C("C"), // Celsius
    KPCM("kg/m^3"),
    PERCENT("%"),
    M("m"), // Meters
    KPA("kPa"),
    S("s"), // Seconds
    KG("kg"),
    BAR("bar"),
    L("l"), // Liter
    KGH("kg/h"),
    MPS("m/s"),
    RAD("rad"),
    RPM("rpm");
    
    private final String label;
    
    Unit(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
    
    public static Unit resolveByLabel(String label) {
        for ( Unit unit : values() ) {
            if ( unit.getLabel()
                    .equals(label) ) {
                return unit;
            }
        }
        throw new IllegalArgumentException(String.format("Unit: %s does not exist", label));
    }
    
}
