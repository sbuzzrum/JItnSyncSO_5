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

public class MySqlDate {
    public static final int df_mysql_db = 0;
    public static final int df_dbf_db   = 1;
    public static final int df_mssql_db = 2;
    
    private static int db_used = df_mysql_db;
    
    public static void setDBused(int _db_used) {
        db_used = _db_used;
    }
    public static int getDBused() {
        return(db_used);
    }
    public static String javaToDb(Date data) {
        return(javaToDb(data, db_used));
        /*
        if(db_used == df_mysql_db)
            return(javaToMysql(data));
        else if(db_used == df_mssql_db)
            return(javaToMssql(data));
        else
            return(javaToDbf(data));
         */
    }    

    public static String javaToDb(Date data, int p_db_used) {
        if(p_db_used == df_mysql_db)
            return(javaToMysql(data));
        else if(p_db_used == df_mssql_db)
            return(javaToMssql(data));
        else
            return(javaToDbf(data));
    }    
    
    private static String javaToMysql(Date data) {
        /* Formato x MySql: yyyy-MM-dd */
        String dataMySql = MyDate.toString(data, "yyyy-MM-dd");
        return((dataMySql.length() > 0 ? "'" + dataMySql + "'" : "NULL"));
    }    

    private static String javaToDbf(Date data) {
        /* Formato x Dbf via ODBC/Windows yyyy-MM-dd */
        /* dai test effettuati sembra il piu versatile: e' consentito il NULL */
        String dataDbf = MyDate.toString(data, "yyyy-MM-dd");
        return((dataDbf.length() > 0 ? "'" + dataDbf + "'" : "NULL"));
    }    

    private static String javaToMssql(Date data) {
        /* Formato x MsSql: dd-MM-yyyy */
        String dataMsSql = MyDate.toString(data, "dd-MM-yyyy");
        return((dataMsSql.length() > 0 ? "'" + dataMsSql + "'" : "NULL"));
    }
    
    /* ************* VECCHI METODI non doverebbero piu' essere utili con l'utilizzo di Date ************* */
    /*
    public static Date dbToJava(MyDate data) {
        if(db_used == df_mysql_db)
            return(mysqlToJava(data));
        else
            return(dbfToJava(data));        
    }

    private static String mysqlToJava(String data) {
        // Formato Atteso yyyy-mm-dd
        // Formato Prodotto dd/mm/yyyy
        String ret = "";
        
        try {
            ret = data.substring(8);
            ret = ret.concat("/");
            ret = ret.concat(data.substring(5,7));
            ret = ret.concat("/");
            ret = ret.concat(data.substring(0,4));
        } catch(Exception e) {
            //ret = "  /  /    ";
            ret = null;
        }
        
        return(ret);
    }    

    private static String dbfToJava(String data) {
        // Formato Atteso yyyy-mm-dd
        // Formato Prodotto dd/mm/yyyy
        String ret = "";
        
        try {
            ret = data.substring(8);
            ret = ret.concat("/");
            ret = ret.concat(data.substring(5,7));
            ret = ret.concat("/");
            ret = ret.concat(data.substring(0,4));
        } catch(Exception e) {
            //ret = "  /  /    ";
            ret = null;
        }
        
        return(ret);
    }    
*/
    
    /* ALTRI METODI DA VERIFICARE */
    public static int getAnno(String data) {
        /* Formato Atteso dd/mm/yyyy" */
        int begin = 6;
        return(Integer.parseInt(data.substring(begin)));
    }

    public static int getAnnoShort(String data) {
        /* Formato Atteso dd/mm/yyyy" */
        int begin = 8;
        return(Integer.parseInt(data.substring(begin)));
    }
    
    public static int compareDate(String data1, String data2) {
        /* Formato Atteso dd/mm/yyyy */
        int dd1 = Integer.parseInt(data1.substring(0,2));
        int mm1 = Integer.parseInt(data1.substring(3,5));
        int yy1 = Integer.parseInt(data1.substring(6));
        int dd2 = Integer.parseInt(data2.substring(0,2));
        int mm2 = Integer.parseInt(data2.substring(3,5));
        int yy2 = Integer.parseInt(data2.substring(6));
        
        return(yy1*1000+mm1*100+dd1 - yy2*1000+mm2*100+dd2);
    }
    
    public static boolean isBetweenDate(String dataBefore, String dataBetween, String dataAfter) {
        /* Formato Atteso dd/mm/yyyy */
        int dd1 = Integer.parseInt(dataBefore.substring(0,2));
        int mm1 = Integer.parseInt(dataBefore.substring(3,5));
        int yy1 = Integer.parseInt(dataBefore.substring(6));
        
        int dd2 = Integer.parseInt(dataBetween.substring(0,2));
        int mm2 = Integer.parseInt(dataBetween.substring(3,5));
        int yy2 = Integer.parseInt(dataBetween.substring(6));
        
        int dd3 = Integer.parseInt(dataAfter.substring(0,2));
        int mm3 = Integer.parseInt(dataAfter.substring(3,5));
        int yy3 = Integer.parseInt(dataAfter.substring(6));
        
        
        
        return(yy1*1000+mm1*100+dd1 <= yy2*1000+mm2*100+dd2 && 
               yy2*1000+mm2*100+dd2 <= yy3*1000+mm3*100+dd3);
    }

}
