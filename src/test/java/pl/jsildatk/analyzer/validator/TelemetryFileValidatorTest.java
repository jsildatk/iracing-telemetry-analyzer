package pl.jsildatk.analyzer.validator;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;

public class TelemetryFileValidatorTest {
    
    @Test
    public void testValidatingFileWithInvalidFileFormat() {
        // given
        final MultipartFile file = new MockMultipartFile("invalid_file_format.asdf", (byte[]) null);
        
        // when
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TelemetryFileValidator.validateFile(file));
        
        // then
        assertThat(exception.getMessage(), containsString("File must be in csv format"));
    }
    
}