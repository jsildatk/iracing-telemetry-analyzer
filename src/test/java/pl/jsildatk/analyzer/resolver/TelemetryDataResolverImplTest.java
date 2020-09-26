package pl.jsildatk.analyzer.resolver;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.opencsv.CSVReader;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jsildatk.analyzer.dto.SingleTypeData;
import pl.jsildatk.analyzer.dto.TelemetryData;
import pl.jsildatk.analyzer.dto.TelemetryLap;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.util.TimeUtils;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class TelemetryDataResolverImplTest {
    
    private TelemetryDataResolverImpl telemetryDataResolver;
    
    @BeforeEach
    public void setUp() {
        telemetryDataResolver = new TelemetryDataResolverImpl();
    }
    
    @Test
    public void testResolvingHeader() {
        // given
        final String[] header = new String[]{ "Throttle", "Brake", "Clutch" };
        
        // when
        final Map<Integer, Type> result = telemetryDataResolver.resolveHeader(header);
        
        // then
        assertThat(result, allOf(hasEntry(0, Type.Throttle), hasEntry(1, Type.Brake), hasEntry(2, Type.Clutch)));
    }
    
    @Test
    public void testResolvingHeaderForF1Car() {
        // given
        final String[] header = new String[]{ "Throttle", "Brake", "Clutch", "dcMGUKDeployMode", "DRS_Status" };
        
        // when
        final Map<Integer, Type> result = telemetryDataResolver.resolveHeader(header);
        
        // then
        assertThat(result, allOf(hasEntry(0, Type.Throttle), hasEntry(1, Type.Brake), hasEntry(2, Type.Clutch), hasEntry(3, Type.dcMGUKDeployMode),
                hasEntry(4, Type.DRS_Status)));
    }
    
    @Test
    public void testResolvingData() throws Exception {
        // given
        final Map<Integer, Type> map = createIntegerToTypeMapping();
        final String[] fileContent = readFile("src/test/resources/resolving_data.csv");
        
        // when
        final List<TelemetryData> result = telemetryDataResolver.resolve(map, fileContent);
        
        // then
        assertThat(result, hasSize(Type.values().length));
    }
    
    @Test
    public void testResolvingLapsData() {
        // given
        final TelemetryData brakeData1 = new TelemetryData(Type.Brake, 0.0);
        final TelemetryData brakeData2 = new TelemetryData(Type.Brake, 1.0);
        final TelemetryData clutchData = new TelemetryData(Type.Clutch, 34.2);
        final TelemetryData throttleData = new TelemetryData(Type.Throttle, 12.2);
        final TelemetryData gearData = new TelemetryData(Type.Gear, 1);
        final TelemetryData gearData1 = new TelemetryData(Type.Gear, 5);
        final TelemetryData rpmData = new TelemetryData(Type.RPM, 2323.12);
        final TelemetryData rpmData1 = new TelemetryData(Type.RPM, 6455.12);
        final TelemetryData steeringAngleData = new TelemetryData(Type.SteeringWheelAngle, -123);
        final TelemetryData steeringAngleData1 = new TelemetryData(Type.SteeringWheelAngle, 123);
        final TelemetryData lapData = new TelemetryData(Type.Lap, 1);
        final TelemetryData lapTimeData = new TelemetryData(Type.LapCurrentLapTime, 23.23);
        final TelemetryData speedData1 = new TelemetryData(Type.Speed, 30);
        final TelemetryData speedData2 = new TelemetryData(Type.Speed, 56.44);
        
        // when
        final List<TelemetryLap> result = telemetryDataResolver.getLapsData(
                createTelemetryData(brakeData1, brakeData2, clutchData, throttleData, gearData, gearData1, rpmData, rpmData1, steeringAngleData,
                        steeringAngleData1, lapData, lapTimeData, speedData1, speedData2));
        final TelemetryLap resultLap = result.get(0);
        final List<SingleTypeData> resultLapData = resultLap.getData();
        
        // then
        assertThat(result, hasSize(1));
        
        assertThat(resultLap.getNumber(), is((int) lapData.getValue()));
        assertThat(resultLap.getLapTime(), is(TimeUtils.formatLapTime(lapTimeData.getValue())));
        assertThat(resultLap.getMinGear(), is((int) gearData.getValue()));
        assertThat(resultLap.getMaxGear(), is((int) gearData1.getValue()));
        assertThat(resultLap.getMinRpm(), is(rpmData.getValue()));
        assertThat(resultLap.getMaxRpm(), is(rpmData1.getValue()));
        assertThat(resultLap.getMinSteeringAngle(), is(Math.toDegrees(steeringAngleData.getValue())));
        assertThat(resultLap.getMaxSteeringAngle(), is(Math.toDegrees(steeringAngleData1.getValue())));
        assertThat(resultLap.getMinSpeed(), is(108.0));
        assertThat(resultLap.getMaxSpeed(), is(203.184));
        
        assertThat(resultLapData, hasSize(Type.values().length));
        assertThat(telemetryDataResolver.getValuesByType(resultLapData, Type.Brake), contains(brakeData1.getValue(), brakeData2.getValue()));
        assertThat(telemetryDataResolver.getValuesByType(resultLapData, Type.Clutch), contains(100.0 - clutchData.getValue()));
        assertThat(telemetryDataResolver.getValuesByType(resultLapData, Type.Throttle), contains(throttleData.getValue()));
        assertThat(telemetryDataResolver.getValuesByType(resultLapData, Type.Gear), contains(gearData.getValue(), gearData1.getValue()));
        assertThat(telemetryDataResolver.getValuesByType(resultLapData, Type.RPM), contains(rpmData.getValue(), rpmData1.getValue()));
        assertThat(telemetryDataResolver.getValuesByType(resultLapData, Type.SteeringWheelAngle),
                contains(Math.toDegrees(steeringAngleData.getValue()), Math.toDegrees(steeringAngleData1.getValue())));
        assertThat(telemetryDataResolver.getValuesByType(resultLapData, Type.Lap), contains(lapData.getValue()));
        assertThat(telemetryDataResolver.getValuesByType(resultLapData, Type.Speed), contains(108.0, 203.184));
    }
    
    private Map<Integer, Type> createIntegerToTypeMapping() {
        final Map<Integer, Type> map = Maps.newHashMap();
        int i = 0;
        for ( Type type : Type.values() ) {
            map.put(i, type);
            i++;
        }
        return map;
    }
    
    private List<List<TelemetryData>> createTelemetryData(TelemetryData... data) {
        return ImmutableList.of(Lists.newArrayList(data));
    }
    
    private String[] readFile(String path) throws Exception {
        CSVReader csvReader = new CSVReader(new FileReader(path));
        return csvReader.readAll()
                .get(0);
    }
    
}