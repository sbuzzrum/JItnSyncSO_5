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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MyDateUtil {
    
    private final static String arrMesi[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                                             "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private final static String arrNum[]  = {"01", "02", "03", "04", "05", "06", 
                                             "07", "08", "09", "10", "11", "12"};
    
                                             
    public static int getAnnoShortInt() {
        return(Integer.parseInt(getAnnoShortStr()));
    }
     
    public static String getAnnoShortStr() {
        return(getToday_ggmmyy().substring(4));
        //return(new Date().toString().substring(27));
    }
    
    public static Date getToday() {
        return(new Date());
    }

    public static String getToday(String format) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        return(dateFormatter.format(new Date()));
    }

    public static String getHHMM() {
        /* Formato HH:MM */
        return(getTime().substring(0,5));
    }

    public static String getShortHHMM() {
        /* Formato HHMM */
        String sTime = getTime();
        return(sTime.substring(0,2) + sTime.substring(3,5));
    }

    public static String getShortHHMMSS() {
        /* Formato HHMMSS */
        String sTime = getTime();
        return(sTime.substring(0,2) + sTime.substring(3,5) + sTime.substring(6,8));
    }
    
    public static String getTime() {
        /* Formato HH:MM:SS */
        return(new Date().toString().substring(11,19));
    }
    
    private static String getMonthNumber(String pMese) {
        String nMese = "";
        for(int i = 0; i < 12; i++)
            if(pMese.equals(arrMesi[i])) {
                nMese = arrNum[i];
                break;
            }       
        return(nMese);
    }

    public static int getMonth(String pJavaDate) {
        return(Integer.parseInt(pJavaDate.substring(3,5)));
    }

    public static String getToday_ggmmyy() {
        /* Formato ddmmaa */
        String format = "ddMMyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        return(dateFormatter.format(new Date()));
        /*
        String unix_date = SimpleDateFormat() new Date().toString();
        String gg = unix_date.substring(8,10);
        String mm = getMonthNumber(unix_date.substring(4,7));
        String yy = unix_date.substring(26);
        System.out.println(unix_date);
        System.out.println(gg + mm + yy);
        return(gg + mm + yy);
         */
    }
    
    public static String getDateFormat(Date data, String format) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        return(dateFormatter.format(data));
    }

    public static Date getDateFormat(String data, String format) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        try {
            return(dateFormatter.parse(data));
        } catch(Exception e) {
            return(null);
        }
    }
    
    public static int getToday_DOY() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return(calendar.get(Calendar.DAY_OF_YEAR));
    }
    
    /** 
     * Input:  dd/mm/yy o dd/mm/yyyy 
     * Output: dd/mm/yy o dd/mm/yyyy
     */
    public static String makeExpireDate(String data, int gg) {
        String ret = "";
        String sdd, smm, scc, syy;
        int dd, mm, yy;
        
        try {
            dd = Integer.parseInt(data.substring(0,2));
            mm = Integer.parseInt(data.substring(3,5));
            
            // data.length() = 8 o 10
            if(data.length() == 10) {
                scc = data.substring(6,8);
                yy = Integer.parseInt(data.substring(8,10));
            }
            else {
                scc = "";
                yy = Integer.parseInt(data.substring(6,8));
            }
            Calendar calendar = new GregorianCalendar(yy, mm -1, dd);
            calendar.add(Calendar.DATE, gg);
            
            sdd = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            smm = Integer.toString(calendar.get(Calendar.MONTH) +1);
            syy = Integer.toString(calendar.get(Calendar.YEAR));
            
            ret += (sdd.length() == 1 ? "0" : "") + sdd;
            ret += "/" + (smm.length() == 1 ? "0" : "") + smm;
            ret += "/" + scc + (syy.length() == 1 ? "0" : "") + syy;            
        }
        catch(Exception e) {
            ret = data;
            System.out.println("ERROR MyDate.makeExpireDate()");
        }
        
        return(ret);
    }

    public static Date addToDate(Date data, int gg) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(data);
        
        calendar.add(Calendar.DATE, gg);
               
        return(calendar.getTime());
    }
    
    /** 
     * Input:  ddmmyy
     * Output: ddmmyy
     */    
    public static String addToData_ddmmyy(String data, int gg) {
        int dd = Integer.parseInt(data.substring(0, 2));
        int mm = Integer.parseInt(data.substring(2, 4));
        int yy = Integer.parseInt(data.substring(4, 6));

        Calendar calendar = new GregorianCalendar(yy, mm -1, dd);
        calendar.add(Calendar.DATE, gg);
        
        String sdd = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String smm = Integer.toString(calendar.get(Calendar.MONTH) +1);
        String syy = Integer.toString(calendar.get(Calendar.YEAR));
        
        String ret = "";
        ret += (sdd.length() == 1 ? "0" : "") + sdd;
        ret += (smm.length() == 1 ? "0" : "") + smm;
        ret += (syy.length() == 1 ? "0" : "") + syy; 
        
        return(ret);
    }

    /** 
     * Input:  HH.MM.SS or HH.MM
     * Output: time1>time2 --> 1; time1=time2 --> 0; time1<time2 --> -1
     */    
    public static int compareTime(String time1, String time2) {
        int ret = 0;
        
        int HH1 = Integer.parseInt(time1.substring(0,2));
        int MM1 = Integer.parseInt(time1.substring(3,5));
        int SS1;
        if(time1.length() == 8)
            SS1 = Integer.parseInt(time1.substring(6,8));
        else
            SS1 = 0;
                    
        int HH2 = Integer.parseInt(time2.substring(0,2));
        int MM2 = Integer.parseInt(time2.substring(3,5));
        int SS2;
        if(time2.length() == 8)
            SS2 = Integer.parseInt(time2.substring(6,8));
        else
            SS2 = 0;
        
        if(HH1 > HH2)
            ret = 1;
        else if(HH1 < HH2)
            ret = -1;
        else  {
            if(MM1 > MM2)
                ret = 1;
            else if(MM1 < MM2)
                ret = -1;
            else  {
                if(SS1 > SS2)
                    ret = 1;
                else if(SS1 < SS2)
                    ret = -1;
                else 
                    ret = 0;
            }
        }
        
        return(ret);
    }
   
    public static long getTimeInSeconds(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return(cal.get(cal.HOUR_OF_DAY) * 3600 + cal.get(cal.MINUTE) * 60 + cal.get(cal.SECOND));
    }
    
    
    public static int timeCompare(Date data1, Date data2) {
        int ret = 0;    // uguale per default
        long timeInSeconds1 = (data1 != null ? getTimeInSeconds(data1) : -1);
        long timeInSeconds2 = (data2 != null ? getTimeInSeconds(data2) : -1);
        
        if(timeInSeconds1 > timeInSeconds2)
            ret = 1;
        else if(timeInSeconds1 < timeInSeconds2)
            ret = -1;
        
        return(ret);
    }
    
    public static Date setDateTime(Date data1, Date data2) {
        Calendar calTimeSource = Calendar.getInstance();
        Calendar calTarget = Calendar.getInstance();
        
        calTimeSource.setTime(data2);
        calTarget.setTime(data1);
        calTarget.set(calTarget.HOUR_OF_DAY, calTimeSource.get(calTimeSource.HOUR_OF_DAY));
        calTarget.set(calTarget.MINUTE, calTimeSource.get(calTimeSource.MINUTE));
        calTarget.set(calTarget.SECOND, calTimeSource.get(calTimeSource.SECOND));
        
        return(calTarget.getTime());
    }
    
}
