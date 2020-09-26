package pl.jsildatk.analyzer.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;
import pl.jsildatk.analyzer.dto.SingleTypeData;
import pl.jsildatk.analyzer.dto.TelemetryDTO;
import pl.jsildatk.analyzer.dto.TelemetryInfo;
import pl.jsildatk.analyzer.dto.TelemetryLap;
import pl.jsildatk.analyzer.parser.CarType;
import pl.jsildatk.analyzer.parser.Type;
import pl.jsildatk.analyzer.resolver.TelemetryDataResolver;

import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

public class GuiServiceImplTest {
    
    private GuiService guiService;
    
    @BeforeEach
    public void setUp() {
        TelemetryDataResolver telemetryDataResolver = mock(TelemetryDataResolver.class);
        guiService = new GuiServiceImpl(telemetryDataResolver);
        when(telemetryDataResolver.getLapsData(Mockito.any())).thenReturn(
                ImmutableList.of(new TelemetryLap(1, "1", 1, 5, 100, 200, -100, 100,
                        10, 20, ImmutableList.of(new SingleTypeData(Type.Gear, ImmutableList.of(2.0, 3.0))))));
    }
    
    @Test
    public void testCreateModelForLMP2() {
        // given
        final TelemetryDTO telemetryDTO =
                new TelemetryDTO(ImmutableList.of(), new TelemetryInfo("test", CarType.LMP2_DallaraP217, "test", "test", "test",
                        "test", "test", "test"));
        
        // when
        final ModelMap result = guiService.createModel(telemetryDTO);
        
        // then
        assertThat(result, aMapWithSize(4));
        assertThat(result.get("title"), is("test - " + CarType.LMP2_DallaraP217.getOriginalName() + " - test - test"));
        assertThat(result.get("subtitle"), is("test - test - test - test"));
        assertThat(result.get("laps"), is(notNullValue()));
        assertThat((Map<String, String>) result.get("columns"), aMapWithSize(5));
    }
    
    @Test
    public void testCreateModelForF1() {
        // given
        final TelemetryDTO telemetryDTO =
                new TelemetryDTO(ImmutableList.of(), new TelemetryInfo("test", CarType.F1_McLarenMp430, "test", "test", "test",
                        "test", "test", "test"));
        
        // when
        final ModelMap result = guiService.createModel(telemetryDTO);
        
        // then
        assertThat(result, aMapWithSize(4));
        assertThat(result.get("title"), is("test - " + CarType.F1_McLarenMp430.getOriginalName() + " - test - test"));
        assertThat(result.get("subtitle"), is("test - test - test - test"));
        assertThat(result.get("laps"), is(notNullValue()));
        assertThat((Map<String, String>) result.get("columns"), aMapWithSize(7));
    }
    
}