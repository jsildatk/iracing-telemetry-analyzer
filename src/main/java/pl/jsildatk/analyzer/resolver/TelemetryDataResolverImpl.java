package pl.jsildatk.analyzer.resolver;

import com.google.common.annotations.VisibleForTesting;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.parser.Unit;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class TelemetryDataResolverImpl implements TelemetryDataResolver {
    
    private static final String MAPPING_DATA_PATH = "src/main/resources/mapping.csv";
    
    private final Map<Type, Unit> mapping = createDataFromCsv();
    
    @Override
    public List<TelemetryData> resolve(String[] line) {
        final List<TelemetryData> data = new ArrayList<>();
        for ( int i = 0; i < line.length; i++ ) {
            final Type type = Type.resolveByIndex(i);
            if ( type != null ) {
                data.add(new TelemetryData(type, mapping.get(type), Double.parseDouble(line[i])));
            }
        }
        return data;
    }
    
    private Map<Type, Unit> createDataFromCsv() {
        final Map<Type, Unit> data = new HashMap<>();
        try {
            final CSVReader csvReader = new CSVReader(new FileReader(MAPPING_DATA_PATH));
            for ( String[] line : csvReader.readAll() ) {
                data.put(Type.valueOf(line[0]), Unit.resolveByLabel(line[1].trim()));
            }
        } catch ( IOException | CsvException e ) {
            log.warn(Arrays.toString(e.getStackTrace()));
        }
        
        return data;
    }
    
    @VisibleForTesting
    Map<Type, Unit> getMapping() {
        return mapping;
    }
    
}
