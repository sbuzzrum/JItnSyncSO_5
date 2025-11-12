/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ITN_SYNC_SO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author lucio
 */
public class GlobalParameters {
    
    private static final String version         = "Release 1.0.0";
    
    private static final String logger_params_name_key  = "logger_params";
//    private static final String txOnly_params_key       = "tx_only";
    private static final String hl7IpAddress_key        = "hl7IpAddress";
    private static final String hl7Port_key             = "hl7Port";
    private static final String hl7PatternFile_key      = "hl7PatternFile";
    
    private static final String dbf_tra_sync_name_key   = "dbf_tra_sync";
    private static final String dbf_tkt_sync_name_key   = "dbf_tkt_sync";
    private static final String dbf_use_sync_name_key   = "dbf_use_sync";
    private static final String dbf_params_name_key     = "dbf_params";
    
    private static String dbf_tra_sync_name;
    private static String dbf_tkt_sync_name;
    private static String dbf_use_sync_name;
    private static String dbf_params_name;
    
    private static String logger_params_name;
    
    private static String hl7IpAddress;
    private static String hl7Port;
    private static String hl7PatternFile;
    
    private static Properties generalProperties;
    
    public static void readProperties(String configFileName){
        generalProperties = new Properties();
        try {
            generalProperties.load(new FileInputStream(configFileName));
            //dbProperties.load(new FileInputStream(System.getProperty("user.dir") + "/rdbms.prop"));
            loadProperties();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void loadProperties(){
        logger_params_name  = generalProperties.getProperty(logger_params_name_key);
//        txOnly_params       = generalProperties.getProperty(txOnly_params_key);
        hl7IpAddress        = generalProperties.getProperty(hl7IpAddress_key);
        hl7Port             = generalProperties.getProperty(hl7Port_key);
        hl7PatternFile      = generalProperties.getProperty(hl7PatternFile_key);
        dbf_tra_sync_name   = generalProperties.getProperty(dbf_tra_sync_name_key);
        dbf_tkt_sync_name   = generalProperties.getProperty(dbf_tkt_sync_name_key);
        dbf_use_sync_name   = generalProperties.getProperty(dbf_use_sync_name_key);
        dbf_params_name     = generalProperties.getProperty(dbf_params_name_key);
    }

    public static String showProperties() {
        StringBuffer ret = new StringBuffer();
        ret.append("****************************************************\n");
        ret.append("*** " + version                                    + "\n");
        
        ret.append(logger_params_name_key  + "=" + logger_params_name  + "\n");
//        ret.append(txOnly_params_key       + "=" + txOnly_params       + "\n");
        ret.append(hl7IpAddress_key        + "=" + hl7IpAddress        + "\n");
        ret.append(hl7Port_key             + "=" + hl7Port             + "\n");
        ret.append(hl7PatternFile_key      + "=" + hl7PatternFile      + "\n");
        ret.append(dbf_tra_sync_name_key   + "=" + dbf_tra_sync_name   + "\n");
        ret.append(dbf_tkt_sync_name_key   + "=" + dbf_tkt_sync_name   + "\n");
        ret.append(dbf_use_sync_name_key   + "=" + dbf_use_sync_name   + "\n");
        ret.append(dbf_params_name_key     + "=" + dbf_params_name     + "\n");
        
        ret.append("****************************************************\n\n");
        return(ret.toString());
    }
    
    public static String getDbf_use_sync_name() {
        return dbf_use_sync_name;
    }

    /**
     * @return the dbfName
     */
    public static String getDbf_tra_sync_name() {
        return dbf_tra_sync_name;
    }

    /**
     * @return the dbfName
     */
    public static String getDbf_tkt_sync_name() {
        return dbf_tkt_sync_name;
    }
    
    public static String getDbf_params_name() {
        return dbf_params_name;
    }
    /**
     * @return the txOnly_params
     */
//    public static boolean getTxOnly_params() {
//        if(txOnly_params == null)
//            return false;
//        else
//            return txOnly_params.equalsIgnoreCase("YES");
//    }

    /**
    /**
     * @return the logger_params_name
     */
    public static String getLogger_params_name() {
        return logger_params_name;
    }

    public static String getHl7IpAddress() {
        return hl7IpAddress;
    }

    public static String getHl7Port() {
        return hl7Port;
    }

    public static String getHL7file() {
        return(hl7PatternFile != null && hl7PatternFile.trim().length() > 0 ? hl7PatternFile : "");
    }

    public static boolean isHL7() {
        return(hl7IpAddress != null && hl7IpAddress.trim().length() > 0 && hl7Port != null && hl7Port.trim().length() > 0);
    }

}
