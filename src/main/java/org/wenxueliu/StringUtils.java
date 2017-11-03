package org.wenxueliu;

public final class StringUtils {

    private StringUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static Double convertToDouble(String str) {
      if (str == null) {
        return null;
      }
      return Double.valueOf(str);
    }

    public static boolean isNullOrBlank(String st) {
      return st == null || st.trim().length() == 0;
    }

    public static String getDefaultIfNull(final String st, final String defaultSt) {
      return st == null ? defaultSt : st;
    }

    public static Integer convertToInt(String str) {
      if (str == null || str.trim().length() == 0) {
        throw new IllegalArgumentException("String must be not null or empty");
      }
      return Integer.valueOf(str);
    }

    public static String concat(String... sts) {
      String retVal = null;
      if (sts != null && sts.length > 0) {
        StringBuilder sb = new StringBuilder();
        for (String st : sts) {
          if (st != null) {
            sb.append(st);
          }
        }
        retVal = sb.toString();
      }
      return retVal;
    }

}
