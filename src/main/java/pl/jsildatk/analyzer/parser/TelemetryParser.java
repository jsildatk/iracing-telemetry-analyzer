package pl.jsildatk.analyzer.parser;

import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryInfo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public interface TelemetryParser {
    
    /**
     * Parse first 8 lines of telemetry files to TelemetryInfo object
     *
     * @param inputStreamReader reader with data from telemetry file
     * @return TelemetryInfo object
     * @throws IOException if file was not found
     * @since 1.0.0
     */
    TelemetryInfo parseTelemetryInfo(InputStreamReader inputStreamReader) throws IOException;
    
    List<List<TelemetryData>> parseTelemetryData(InputStreamReader inputStreamReader) throws IOException;
    
}
