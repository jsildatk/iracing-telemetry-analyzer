package pl.jsildatk.analyzer.parser;

import lombok.NonNull;

/**
 * Mapping for cars from CSV file. Name is the same as value from CSV file.
 * Such a mapping is needed for displaying additional telemetry columns on frontend.
 *
 * @since 1.0.0
 */
public enum CarType {
    
    LMP2_DallaraP217("dallarap217"),
    F1_McLarenMp430("mclarenmp430"),
    GT3_AudiR8("audir8gt3"),
    GTE_Porsche991RSR("porsche991rsr"),
    Porsche911Cup("porsche911cup");
    
    private final String originalName;
    
    CarType(String originalName) {
        this.originalName = originalName;
    }
    
    public String getOriginalName() {
        return originalName;
    }
    
    public static CarType getByOriginalName(@NonNull String name) {
        for ( CarType type : values() ) {
            if ( type.originalName.equals(name) ) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Type: %s does not exist", name));
    }
    
}
