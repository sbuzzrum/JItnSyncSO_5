/*
 * PmsDbOrdineUtil.java
 *
 * Created on 18 maggio 2003, 16.46
 */

package ITN_SYNC_SO.util;

/**
 *
 * @author  Hal9154
 */
public class MyUtil {
    
    public static char df_char_apice = 39;
    public static String df_str_apice = "'";
    
    public static String addDoppioApice(String _buffer) {
        
        if(_buffer == null)
            return(_buffer);
        
        StringBuffer sb = new StringBuffer(_buffer);

        int pos = 0;
        while(pos > -1) {
            pos = sb.indexOf(df_str_apice, pos);
            if(pos > -1) {
                sb.insert(pos, df_char_apice);
                pos += 2;
            } 
        }
        return(sb.toString());
    }

    public static String getString(String stringa) {
        if(stringa == null)
            stringa = "";
        return(stringa);
    }

    public static String replicate(String a, int n) {
        StringBuffer ret = new StringBuffer();
        while(n-- > 0)
            ret.append(a);
        return(ret.toString());
    }

}
