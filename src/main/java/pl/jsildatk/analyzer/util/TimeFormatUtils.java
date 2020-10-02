package pl.jsildatk.analyzer.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for formatting time.
 *
 * @author Jakub Sildatk
 */
@UtilityClass
public class TimeFormatUtils {
    
    /**
     * Format session time to: hh:MM:ss
     *
     * @param time time in seconds as double
     * @return formatted time as string
     * @since 1.0.0
     */
    public String formatSessionTime(double time) {
        final int hours = (int) Math.floor(time / 3600);
        final int minutes = (int) Math.floor((time % 3600) / 60);
        final int seconds = (int) Math.floor(time % 60);
        
        final StringBuilder result = new StringBuilder();
        if ( hours > 0 ) {
            result.append(hours)
                    .append(":")
                    .append((minutes < 10 ? "0" : ""));
        }
        result.append(minutes)
                .append(":")
                .append((seconds < 10 ? "0" : ""));
        result.append(seconds);
        return result.toString();
    }
    
    /**
     * Format lap time to: MM:ss.SSS
     *
     * @param lapTime lap time as double
     * @return formatted time as string
     * @since 1.0.0
     */
    public String formatLapTime(double lapTime) {
        final int minutes = (int) Math.floor(lapTime / 60);
        final double seconds = BigDecimal.valueOf(lapTime - minutes * 60)
                .setScale(3, RoundingMode.HALF_EVEN)
                .doubleValue();
        final int decimalIndex = String.valueOf(seconds)
                .indexOf(".");
        
        final String secondsAsString = String.valueOf(seconds);
        final String integerPart = secondsAsString.substring(0, decimalIndex);
        String decimalPart = secondsAsString.substring(decimalIndex + 1);
        
        if ( decimalPart.length() == 1 ) {
            decimalPart = decimalPart + "00";
        } else if ( decimalPart.length() == 2 ) {
            decimalPart = decimalPart + "0";
        }
        
        return minutes + ":" + (seconds < 10 ? "0" : "") + integerPart + "." + decimalPart;
    }
    
}
