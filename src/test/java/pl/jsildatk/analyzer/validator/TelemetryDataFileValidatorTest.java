package pl.jsildatk.analyzer.validator;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TelemetryDataFileValidatorTest {
    
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