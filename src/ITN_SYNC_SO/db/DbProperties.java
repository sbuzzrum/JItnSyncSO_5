/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author d.saponaro
 */
public class DbProperties {

    private final static int    rdbmsNONE       = 0;
    private final static int    rdbmsMSSQL      = 1;
    private final static int    rdbmsORACLE     = 2;
    private final static int    rdbmsHL7        = 3;

    private final static String rdbmsNameNONE   = "UNKNOWN";
    private final static String rdbmsNameMSSQL  = "MSSQL";
    private final static String rdbmsNameORACLE = "ORACLE";
    private final static String rdbmsNameHL7    = "HL7";

    private final static int    progNONE        = 0;
    private final static int    progORMAW       = 1;
    private final static int    progDIGIS       = 2;
    private final static int    progSIX         = 3;
    private final static int    progNEGRAR      = 4;
    private final static int    progISMETT      = 5;
    private final static int    progORMAW_V2    = 6;

    private final String progNameNONE    = "UNKNOWN";
    private final String progNameORMAW   = "ORMAWIN";
    private final String progNameDIGIS   = "DIGISTAT";
    private final String progNameSIX     = "SIX";
    private final String progNameNEGRAR  = "NEGRAR";
    private final String progNameISMETT  = "ISMETT";
    private final String progNameORMAW2   = "ORMAWIN_2";

    private final String progType_key            = "progType";
    private final String rdbmsType_key           = "rdbmsType";
    private final String rdbmsIpAddress_key      = "rdbmsIpAddress";
    private final String rdbmsPort_key           = "rdbmsPort";
    private final String rdbmsDbName_key         = "rdbmsDbName";
    private final String rdbmsUsername_key       = "rdbmsUsername";
    private final String rdbmsPassword_key       = "rdbmsPassword";
    private final String rdbmsDbUsername_key     = "rdbmsDbUsername";
    private final String rdbmsDbViewSale_key     = "rdbmsDbViewSale";
//    private final String dbf_tra_sync_name_key   = "dbf_tra_sync";
//    private final String dbf_tkt_sync_name_key   = "dbf_tkt_sync";
//    private final String dbf_use_sync_name_key   = "dbf_use_sync";
    private final String dbf_params_name_key     = "dbf_params";
//    private final String logger_params_name_key  = "logger_params";
//    private final String txOnly_params_key       = "tx_only";
    private final String txrxType_params_key       = "txrx_type";
//    private final String hl7PatternFile_key      = "hl7PatternFile";
    private final String useTimestamp_params_key = "use_timestamp";
    private final String sqlLogger_key           = "sql_logger";
    private final String sqlDebugLog_key         = "debug_sql_logger";
    
    private final String wsApiEndpoint_key       = "wsApiEndpoint";
    private final String wsApiKeyHeader_key      = "wsApiKeyHeader";
    private final String wsApiKey_key            = "wsApiKey";

    //private final String logger_prefix_key       = "logger_prefix";
    
    private int    progType;
    private int    rdbmsType;
    private String rdbmsIpAddress;
    private String rdbmsPort;
    private String rdbmsDbName;
    private String rdbmsUsername;
    private String rdbmsPassword;
    private String rdbmsDbUsername;
    private String rdbmsDbViewSale;
//    private String dbf_tra_sync_name;
//    private String dbf_tkt_sync_name;
//    private String dbf_use_sync_name;
    private String dbf_params_name;
//    private String logger_params_name;
//    private String txOnly_params;
    private String txrxType_params;
//    private String hl7PatternFile;
    
    private String useTimestamp_params;
    private String sqlLogger_params;
    private String sqlDebugLog_params;
    
    private String wsApiEndpoint;
    private String wsApiKeyHeader;
    private String wsApiKey;

    //private String loggerPrefix;

    private Properties dbProperties;

    public void readDbProperties(String configFileName){
        dbProperties = new Properties();
        try {
            dbProperties.load(new FileInputStream(configFileName));
            //dbProperties.load(new FileInputStream(System.getProperty("user.dir") + "/rdbms.prop"));
            loadDbProperties();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void loadDbProperties(){
        progType            = loadMssqlType(dbProperties.getProperty(progType_key));
        rdbmsType           = loadMssqlType(dbProperties.getProperty(rdbmsType_key));
        rdbmsIpAddress      = dbProperties.getProperty(rdbmsIpAddress_key);
        rdbmsPort           = dbProperties.getProperty(rdbmsPort_key);
        rdbmsDbName         = dbProperties.getProperty(rdbmsDbName_key);
        rdbmsUsername       = dbProperties.getProperty(rdbmsUsername_key);
        rdbmsPassword       = dbProperties.getProperty(rdbmsPassword_key);
        rdbmsDbUsername     = dbProperties.getProperty(rdbmsDbUsername_key);
        rdbmsDbViewSale     = dbProperties.getProperty(rdbmsDbViewSale_key);
        
//        logger_params_name  = dbProperties.getProperty(logger_params_name_key);
//        txOnly_params       = dbProperties.getProperty(txOnly_params_key);
        txrxType_params     = dbProperties.getProperty(txrxType_params_key);
//        hl7PatternFile      = dbProperties.getProperty(hl7PatternFile_key);
//        dbf_tra_sync_name   = dbProperties.getProperty(dbf_tra_sync_name_key);
//        dbf_tkt_sync_name   = dbProperties.getProperty(dbf_tkt_sync_name_key);
//        dbf_use_sync_name   = dbProperties.getProperty(dbf_use_sync_name_key);
        dbf_params_name     = dbProperties.getProperty(dbf_params_name_key);
        useTimestamp_params = dbProperties.getProperty(useTimestamp_params_key);
        sqlLogger_params    = dbProperties.getProperty(sqlLogger_key);
        sqlDebugLog_params  = dbProperties.getProperty(sqlDebugLog_key);
        wsApiEndpoint  = dbProperties.getProperty(wsApiEndpoint_key);
        wsApiKeyHeader  = dbProperties.getProperty(wsApiKeyHeader_key);
        wsApiKey = dbProperties.getProperty(wsApiKey_key);
        //loggerPrefix = dbProperties.getProperty(loggerPrefix);
    }

    public String showDbProperties() {
        StringBuffer ret = new StringBuffer();
        ret.append("****************************************************\n");
//        ret.append("*** " + version                                    + "\n");
        //ret.append(logger_prefix_key       + "=" + loggerPrefix        + "\n");
        ret.append(progType_key            + "=" + getMssqlProgStr()   + "\n");
        ret.append(rdbmsType_key           + "=" + getMssqlTypeStr()   + "\n");
        ret.append(rdbmsIpAddress_key      + "=" + rdbmsIpAddress      + "\n");
        ret.append(rdbmsPort_key           + "=" + rdbmsPort           + "\n");
        ret.append(rdbmsDbName_key         + "=" + rdbmsDbName         + "\n");
        ret.append(rdbmsUsername_key       + "=" + rdbmsUsername       + "\n");
        ret.append(rdbmsPassword_key       + "=" + rdbmsPassword       + "\n");
        ret.append(rdbmsDbUsername_key     + "=" + rdbmsDbUsername     + "\n");
//        ret.append(rdbmsDbViewSale_key     + "=" + rdbmsDbViewSale     + "\n");
//        ret.append(logger_params_name_key  + "=" + logger_params_name  + "\n");
//        ret.append(txOnly_params_key       + "=" + txOnly_params       + "\n");
        ret.append(txrxType_params_key       + "=" + txrxType_params       + "\n");
//        ret.append(hl7PatternFile_key      + "=" + hl7PatternFile      + "\n");
//        ret.append(dbf_tra_sync_name_key   + "=" + dbf_tra_sync_name   + "\n");
//        ret.append(dbf_tkt_sync_name_key   + "=" + dbf_tkt_sync_name   + "\n");
//        ret.append(dbf_use_sync_name_key   + "=" + dbf_use_sync_name   + "\n");
        ret.append(dbf_params_name_key     + "=" + dbf_params_name     + "\n");
        ret.append(useTimestamp_params_key + "=" + useTimestamp_params + "\n");
        ret.append(sqlLogger_key           + "=" + sqlLogger_params    + "\n");
        ret.append(sqlDebugLog_key         + "=" + sqlDebugLog_params  + "\n");
        ret.append(wsApiEndpoint_key          + "=" + wsApiEndpoint  + "\n");
        ret.append(wsApiKeyHeader_key      + "=" + wsApiKeyHeader  + "\n");
        ret.append(wsApiKey_key            + "=" + wsApiKey  + "\n");
        ret.append("****************************************************\n\n");
        return(ret.toString());
    }

    /*public String getLoggerPrefix() {
        return loggerPrefix;
    }*/

    /**
     * @return the isMSSQL
     */
    public boolean isMSSQL() {
        return(rdbmsType == rdbmsMSSQL);
    }

    /**
     * @return the isORACLE
     */
    public boolean isORACLE() {
        return(rdbmsType == rdbmsORACLE);
    }

    /**
     * @return the isHL7
     */
    public boolean isHL7() {
        return(rdbmsType == rdbmsHL7);
    }

    /**
     * @return the rdbmsType.toString
     */
    private String getMssqlTypeStr() {
        if(isMSSQL())
            return rdbmsNameMSSQL;
        else if(isORACLE())
            return rdbmsNameORACLE;
        else if(isHL7())
            return rdbmsNameHL7;

        return rdbmsNameNONE;
    }

    /**
     * @return the isORMAWIN
     */
    public boolean isORMAWIN() {
        return(progType == progORMAW);
    }
    /**
     * @return the isORMAWIN
     */
    public boolean isORMAWIN_V2() {
        return(progType == progORMAW_V2);
    }

    /**
     * @return the isDIGISTAT
     */
    public boolean isDIGISTAT() {
        return(progType == progDIGIS);
    }

    /**
     * @return the isSIX
     */
    public boolean isSIX() {
        return(progType == progSIX);
    }

    /**
     * @return the isNEGRAR
     */
    public boolean isNEGRAR() {
        return(progType == progNEGRAR);
    }

    /**
     * @return the isISMETT
     */
    public boolean isISMETT() {
        return(progType == progISMETT);
    }

    /**
     * @return the isSqlLogger
     */
    public boolean isSqlLogger() {
        return(sqlLogger_params.equals("yes"));
    }

    /**
     * @return the isSqlLogger
     */
    public boolean isSqlDebugLog() {
        return(sqlDebugLog_params.equals("yes"));
    }

    /**
     * @return the progType.toString
     */
    private String getMssqlProgStr() {
        if(isORMAWIN())
            return progNameORMAW;
        else if(isDIGISTAT())
            return progNameDIGIS;
        else if(isSIX())
            return progNameSIX;
        else if(isNEGRAR())
            return progNameNEGRAR;
        else if(isISMETT())
            return progNameISMETT;
        else if(isORMAWIN_V2())
            return progNameORMAW2;


        return progNameNONE;
    }

    /**
     * @return the mssqlIpAddress
     */
    private int loadMssqlType(String pType) {
        return(Integer.parseInt(pType.length() == 0 ? "0" : pType));
    }

    /**
     * @return the mssqlIpAddress
     */
    public String getMssqlIpAddress() {
        return rdbmsIpAddress;
    }

    /**
     * @return the mssqlPort
     */
    public String getMssqlPort() {
        return rdbmsPort;
    }

    /**
     * @return the mssqlDbName
     */
    public String getMssqlDbName() {
        return rdbmsDbName;
    }

    /**
     * @return the mssqlUsername
     */
    public String getMssqlUsername() {
        return rdbmsUsername;
    }

    /**
     * @return the mssqlPassword
     */
    public String getMssqlPassword() {
        return rdbmsPassword;
    }

    /**
     * @return the dbfName
     */
//    public String getDbf_use_sync_name() {
//        return dbf_use_sync_name;
//    }

    /**
     * @return the dbfName
     */
//    public String getDbf_tra_sync_name() {
//        return dbf_tra_sync_name;
//    }

    /**
     * @return the dbfName
     */
//    public String getDbf_tkt_sync_name() {
//        return dbf_tkt_sync_name;
//    }

    /**
     * @return the dbf_params_name
     */
    public String getDbf_params_name() {
        return dbf_params_name;
    }

    /**
     * @return the mssqlDbUsername
     */
    public String getMssqlDbUsername() {
        return(rdbmsDbUsername.trim().length() > 0 ? rdbmsDbUsername + "." : "");
    }

    /**
     * @return the mssqlDbViewSale
     */
    public String getMssqlDbViewSale(String default_name) {
        if(rdbmsDbViewSale != null && rdbmsDbViewSale.trim().length() > 0)
            return(rdbmsDbViewSale);
        return(default_name);
    }

    /*
     * @return the useTimestamp_params
     */
    public boolean isUseTimestamp_params() {
        if(useTimestamp_params == null)
            return false;
        else
            return useTimestamp_params.equalsIgnoreCase("YES");
    }

    /**
     * @return the txOnly_params
     */
//    public boolean getTxOnly_params() {
//        if(txOnly_params == null)
//            return false;
//        else
//            return txOnly_params.equalsIgnoreCase("YES");
//    }

    /**
     * @return the txOnly_params
     */
    public boolean isDbTx_params() {
        return isDbTxRx_params(true);
    }
    public boolean isDbRx_params() {
        return isDbTxRx_params(false);
    }
    private boolean isDbTxRx_params(boolean checkTx) {
        if(txrxType_params == null)
            return false;
        else if(checkTx)
            return txrxType_params.equals("0") || txrxType_params.equals("1");
        else
            return txrxType_params.equals("0") || txrxType_params.equals("2");
    }
    
    /**
    /**
     * @return the logger_params_name
     */
//    public String getLogger_params_name() {
//        return logger_params_name;
//    }

//    public String getHL7file() {
//        return(hl7PatternFile.trim().length() > 0 ? hl7PatternFile : "");
//    }

    public String getWsApiEndpoint() {
        return wsApiEndpoint;
    }

    public String getWsApiKeyHeader() {
        return wsApiKeyHeader;
    }

    public String getWsApiKey() {
        return wsApiKey;
    }

}
