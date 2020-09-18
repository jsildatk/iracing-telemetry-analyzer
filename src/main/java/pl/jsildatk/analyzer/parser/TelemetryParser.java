package pl.jsildatk.analyzer.parser;

import com.opencsv.exceptions.CsvValidationException;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryInfo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public interface TelemetryParser {
    
    /**
     * Parse first 8 lines of telemetry files to {@link TelemetryInfo}
     *
     * @param inputStreamReader reader with data from telemetry file
     * @return TelemetryInfo object
     * @throws IOException if file was not found
     * @since 1.0.0
     */
    TelemetryInfo parseTelemetryInfo(InputStreamReader inputStreamReader) throws IOException;
    
    /**
     * Parse rest of telemetry file to list of lists of {@link TelemetryData} objects.
     * It uses {@link pl.jsildatk.analyzer.resolver.TelemetryDataResolver} for all required operations like: resolving header, resolving lap data
     *
     * @param inputStreamReader reader with data from telemetry file
     * @return list of lists of telemetry data objects
     * @throws IOException            if file was not found
     * @throws CsvValidationException if csv file is in invalid format
     * @since 1.0.0
     */
    List<List<TelemetryData>> parseTelemetryData(InputStreamReader inputStreamReader) throws IOException, CsvValidationException;
    
}
