package pl.jsildatk.analyzer.validator;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@UtilityClass
public class TelemetryFileValidator {
    
    /**
     * Check if multipart file is a csv file
     *
     * @param file file
     * @throws IllegalArgumentException if file is not a csv file
     * @since 1.0.0
     */
    public void validateFile(MultipartFile file) throws IllegalArgumentException {
        if ( !Objects.requireNonNull(file.getOriginalFilename())
                .endsWith(".csv") ) {
            throw new IllegalArgumentException("File must be in csv format");
        }
    }
    
}
