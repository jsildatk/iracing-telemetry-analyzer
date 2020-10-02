package pl.jsildatk.analyzer.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeFormatUtilsTest {
    
    @Test
    public void testFormattingSessionTimeWithHours() {
        final String result = TimeFormatUtils.formatSessionTime(4992);
        assertThat(result, is("1:23:12"));
    }
    
    @Test
    public void testFormattingSessionTimeWithHours2() {
        final String result = TimeFormatUtils.formatSessionTime(44592);
        assertThat(result, is("12:23:12"));
    }
    
    @Test
    public void testFormattingSessionTimeWithoutHours() {
        final String result = TimeFormatUtils.formatSessionTime(1402);
        assertThat(result, is("23:22"));
    }
    
    @Test
    public void testFormattingLapTimeWith3Digits() {
        final String result = TimeFormatUtils.formatLapTime(82.323);
        assertThat(result, is("1:22.323"));
    }
    
    @Test
    public void testFormattingLapTimeWith2Digits() {
        final String result = TimeFormatUtils.formatLapTime(82.32);
        assertThat(result, is("1:22.320"));
    }
    
    @Test
    public void testFormattingLapTimeWith1Digit() {
        final String result = TimeFormatUtils.formatLapTime(82.3);
        assertThat(result, is("1:22.300"));
    }
    
}