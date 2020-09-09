package cc.vivp.bankrupt.model;

import java.text.DecimalFormat;
import java.text.ParseException;

public final class AttributeConverters {

  private AttributeConverters() {
    // Utility class.
  }

  public static String convertFromCents(long input) {
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0.00");
    return decimalFormat.format((double) input / 100);
  }

  public static long convertToCents(String input) {
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0.00");
    try {
      return (long) decimalFormat.parse(input).doubleValue() * 100L;
    } catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
