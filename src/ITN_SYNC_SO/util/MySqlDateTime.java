/*
 * MySqlDate.java
 *
 * Created on 28 aprile 2003, 9.39
 */
package ITN_SYNC_SO.util;

/**
 *
 * @author  Hal9154
 */
//import java.util.Calendar;
import java.util.Date;

public class MySqlDateTime {

    /**************** Not Yet Public ************************************/
    private static final int df_mysql_db = 0;
    private static final int df_dbf_db   = 1;
    /**************** Not Yet Public ************************************/
    
    public static final int df_mssql_db  = 2;
    
    private static int db_used = df_mssql_db;
    
    public static void setDBused(int _db_used) {
        db_used = _db_used;
    }
    public static int getDBused() {
        return(db_used);
    }
    public static String javaToDb(Date data) {
        return(javaToDb(data, db_used));
    }    

    public static String javaToDb(Date data, int p_db_used) {
        if(p_db_used == df_mysql_db)
            return(javaToMysql(data));
        else if(p_db_used == df_mssql_db)
            return(javaToMssql(data));
        else
            return(javaToDbf(data));
    }    

    // To Finish ****************************
    private static String javaToMysql(Date data) {
        // Formato x MySql: yyyy-MM-dd
        String dataMySql = MyDate.toString(data, "yyyy-MM-dd");
        return((dataMySql.length() > 0 ? "'" + dataMySql + "'" : "NULL"));
    }    

    // To Finish ****************************
    private static String javaToDbf(Date data) {
        // Formato x Dbf via ODBC/Windows yyyy-MM-dd
        // dai test effettuati sembra il piu versatile: e' consentito il NULL
        String dataDbf = MyDate.toString(data, "yyyy-MM-dd");
        return((dataDbf.length() > 0 ? "'" + dataDbf + "'" : "NULL"));
    }    

    private static String javaToMssql(Date data) {
        // Formato x MsSql: dd-MM-yyyy HH:mm:ss
        String dataMsSql = MyDate.toString(data, "dd-MM-yyyy HH:mm:ss");
        return((dataMsSql.length() > 0 ? "'" + dataMsSql + "'" : "NULL"));
    }

    public static String javaToLastDateTimeString(Date data) {
        // Formato x lastDateTimeString: yyyyMMddHHmmss
        String lastDateTimeString = MyDate.toString(data, "yyyyMMddHHmmss");
        return(lastDateTimeString);
    }

    public static Date javaFromLastDateTimeString(String lastDateTimeString) {
        Date lastDateTime = MyDate.getDate(lastDateTimeString, "yyyyMMddHHmmss");
        return(lastDateTime);
    }

}
