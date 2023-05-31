package hanu.a2_2001040023.Format;

import java.text.DecimalFormat;

public class NumberFormat {

    private DecimalFormat formatter;

    public NumberFormat() {
        formatter = new DecimalFormat("#,###");
        formatter.setDecimalSeparatorAlwaysShown(false);
    }

    /**
     * Formats a number with the specified pattern.
     *
     * @param number the number to format
     * @return the formatted string representation of the number
     */
    public String formatNumber(double number) {
        return formatter.format(number);
    }
}
