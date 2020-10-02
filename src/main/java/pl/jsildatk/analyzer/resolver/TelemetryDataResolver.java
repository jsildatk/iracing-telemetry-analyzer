package pl.jsildatk.analyzer.resolver;

import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryLap;
import pl.jsildatk.analyzer.parser.Type;

import java.util.List;
import java.util.Map;

/**
 * Interface for resolving single data/line from CSV file.
 *
 * @author Jakub Sildatk
 */
public interface TelemetryDataResolver {
    
    /**
     * Resolve header from CSV file in order to get all required columns to create valid telemetry data. <br>
     * It creates map with {@link Integer} as key and {@link Type} as value.
     *
     * @param header from CSV file (it is not the same for all the vehicles)
     * @return mapping for pair {index, {@link Type}} as map
     * @since 1.0.0
     */
    Map<Integer, Type> resolveHeader(String[] header);
    
    /**
     * Resolve single line from CSV file as TelemetryData object. It uses mapping as {index, {@link Type}} pair.
     *
     * @param indexToType header's mapping from CSV file
     * @param line        from csv file
     * @return list of telemetry data containing {@link Type} and value as double
     * @see TelemetryData
     * @since 1.0.0
     */
    List<TelemetryData> resolve(Map<Integer, Type> indexToType, String[] line);
    
    /**
     * Group all telemetry data into connected with lap data. Thanks to this, on GUI specific laps can be selected. <br>
     * All ticks are connected to the specific lap.
     *
     * @param data list of lists of {@link TelemetryData}
     * @return list of {@link TelemetryLap} containing number, lapTime and a list of {@link pl.jsildatk.analyzer.dto.SingleTypeData} with data from telemetry
     * @since 1.0.0
     */
    List<TelemetryLap> getLapsData(List<List<TelemetryData>> data);
    
}
