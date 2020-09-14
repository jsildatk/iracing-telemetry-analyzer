package pl.jsildatk.analyzer.service;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jsildatk.analyzer.dto.TelemetryDTO;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryInfo;
import pl.jsildatk.analyzer.parser.TelemetryParser;
import pl.jsildatk.analyzer.validator.TelemetryFileValidator;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelemetryServiceImpl implements TelemetryService {
    
    private final TelemetryParser telemetryParser;
    
    @Override
    public TelemetryDTO createTelemetry(MultipartFile telemetry) throws IOException {
        log.info("Creating telemetry for file: {}", telemetry.getOriginalFilename());
        final Stopwatch sw = Stopwatch.createStarted();
        TelemetryFileValidator.validateFile(telemetry);
        
        final byte[] content = createTelemetryContent(telemetry);
        InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(content));
        
        final TelemetryInfo telemetryInfo = telemetryParser.parseTelemetryInfo(inputStreamReader);
        inputStreamReader = new InputStreamReader(new ByteArrayInputStream(content));
        
        final List<List<TelemetryData>> telemetryData = telemetryParser.parseTelemetryData(inputStreamReader);
        
        final long timeElapsed = sw.elapsed(TimeUnit.MILLISECONDS);
        log.info("Time elapsed: {} milliseconds", timeElapsed);
        
        return new TelemetryDTO(telemetryData, telemetryInfo);
    }
    
    private byte[] createTelemetryContent(MultipartFile telemetry) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final InputStream inputStream = telemetry.getInputStream();
        IOUtils.copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
}
