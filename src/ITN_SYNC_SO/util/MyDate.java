/*
 * MyDate.java
 *
 * Created on 10 giugno 2003, 16.10
 */

package ITN_SYNC_SO.util;

/**
 *
 * @author  Hal9154
 */
import java.util.Date;

public class MyDate {
    
    public static final String df_date_format_dd_MM_yy         = "dd/MM/yy";
    public static final String df_date_format_dd_MM_yyyy       = "dd/MM/yyyy";
    public static final String df_date_format_dd_MMM_yyyy      = "dd/MMM/yyyy";
    public static final String df_date_format_dd_MMMM_yyyy     = "dd/MMMM/yyyy";
    public static final String df_date_format_ddd_MMM_yyyy     = "E dd/MMM/yyyy";
    public static final String df_date_format_ddd_MMMM_yyyy    = "E dd/MMMM/yyyy";
    public static final String df_date_format_dddd_MMM_yyyy    = "EEEE dd/MMM/yyyy";
    public static final String df_date_format_dddd_MMMM_yyyy   = "EEEE dd/MMMM/yyyy";

    public static final String df_date_format_dd_MM_yyyy_HH_mm = "dd/MM/yyyy HH:mm";
    
    public static final String df_date_format_MM_dd_yy         = "MM/dd/yy";
    public static final String df_date_format_MM_dd_yyyy       = "MM/dd/yyyy";
    
    public static final String df_date_format_MM_dd_yyyy_HH_mm = "MM/dd/yyyy HH:mm";

    public static final String df_date_format_yyyyMMddHHmmss   = "yyyyMMddHHmmss";

    private static final char df_date_format_separator_slash = '/';
    private static final char df_date_format_separator_dot   = '.';
    private static final char df_date_format_separator_minus = '-';
    
    private static String defaultDatePattern   = df_date_format_dd_MM_yy;
    private static char   defaultDateSeparator = df_date_format_separator_slash;

    public static Date getDate(String date) {
        return( (date == null || date.length() == 0 ? null : getDateExec(date, defaultDatePattern)) );
    }

    public static Date getDate(String date, String format) {
        return( (date == null || date.length() == 0 ? null : getDateExec(date, getFormat(format, defaultDateSeparator))) );
    }

    private static Date getDateExec(String date, String format) {
        return(MyDateUtil.getDateFormat(date, format));
    }
    
    public static String toString(Date date) {
        return( (date == null ? "" : toStringExec(date, defaultDatePattern)) );
    }

    public static String toString(Date date, String format) {
        return( (date == null ? "" : toStringExec(date, getFormat(format, defaultDateSeparator))) );
    }

    private static String toStringExec(Date date, String format) {
        return(MyDateUtil.getDateFormat(date, format));
    }
    
    public static void setDefaultDatePattern(String datePattern) {
        defaultDatePattern = getFormat(datePattern, defaultDateSeparator);
    }

    public static void setDefaultDateSeparator(String dateSeparator) {
        defaultDateSeparator = dateSeparator.charAt(0);
        setDefaultDatePattern(defaultDatePattern);
    }

    public static String getDefaultDatePattern() {
        return(defaultDatePattern);
    }

    public static String getDefaultDateSeparator() {
        return("" + defaultDateSeparator);
    }
    
    private static String  getFormat(String format, char target) {
        String ret = "";
        char oldCharOne;
        char oldCharTwo;
        
        if(target == df_date_format_separator_slash) {
            oldCharOne = df_date_format_separator_dot;
            oldCharTwo = df_date_format_separator_minus;
        }
        else if(target == df_date_format_separator_dot) {
            oldCharOne = df_date_format_separator_slash;
            oldCharTwo = df_date_format_separator_minus;
        }
        else /* if(target == df_date_format_separator_minus) */ {
            oldCharOne = df_date_format_separator_dot;
            oldCharTwo = df_date_format_separator_slash;
        }
        ret = format.replace(oldCharOne, target);
        return(ret.replace(oldCharTwo, target));
    }
}
