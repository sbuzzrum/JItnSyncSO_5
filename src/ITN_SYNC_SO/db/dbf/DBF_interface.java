/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO.db.dbf;

/**
 *
 * @author root
 */
public interface DBF_interface {

    // Tabella sync itineris
    public static final String DF_NAME_IDSPEDIZ    = "IDSPEDIZ";   //  N    11,0
    public static final String DF_NAME_DATA        = "DATA";       //  D     8,0
    public static final String DF_NAME_COD_KIT     = "COD_KIT";    //  C    13,0
    public static final String DF_NAME_COD_KIT_NUM = "COD_KIT_N";  //  N    10,0
    public static final String DF_NAME_DEST_CODE   = "DEST_CODE";  //  C     8,0
    public static final String DF_NAME_DESC_KIT    = "DESC_KIT";   //  M    10,0
    public static final String DF_NAME_ID_KIT      = "ID_KIT";     //  C    13,0
    public static final String DF_NAME_ID_KIT_NUM  = "ID_KIT_N";   //  N    10,0
    public static final String DF_NAME_DATA_SCAD   = "DATA_SCAD";  //  D     8,0
    public static final String DF_NAME_COMMAND     = "COMMAND";    //  N     1,0
    public static final String DF_NAME_STATO       = "STATO";      //  N     1,0
    public static final String DF_NAME_LOTTO       = "LOTTO";      //  M    10,0
    public static final String DF_NAME_USE_FLG     = "USE_FLG";    //  C     1,0
    public static final String DF_NAME_USE_SALA    = "USE_SALA";   //  C     8,0
    public static final String DF_NAME_USE_SALADE  = "USE_SALADE"; //  M    10,0
    public static final String DF_NAME_USE_IDINT   = "USE_IDINT";  //  C    11,0
    public static final String DF_NAME_USE_TIINT   = "USE_TIINT";  //  M    10,0
    public static final String DF_NAME_USE_ORA     = "USE_ORA";    //  C    17,0
    public static final String DF_NAME_DATETIME    = "DATETIME";   //  C    14,0
    public static final String DF_NAME_NUM_DDT     = "NUM_DDT";    //  C     8,0

    // Tabella sync tipokit
    public static final String DF_NAME_TKIT_COMMAND     = "COMMAND";    //  N     1,0
    public static final String DF_NAME_TKIT_DATAREV     = "DATA_REV";   //  D     8,0
    public static final String DF_NAME_TKIT_COD_KIT     = "COD_KIT";    //  C    13,0
    public static final String DF_NAME_TKIT_COD_KIT_NUM = "COD_KIT_N";  //  N    10,0
    public static final String DF_NAME_TKIT_DESC_KIT    = "DESC_KIT";   //  M    10,0
    public static final String DF_NAME_TKIT_TIPO        = "TIPO_KIT";   //  N     1,0

    // Tabella use_sync itineris
    public static final String DF_NAME_USE_IDUTILIZ    = "IDUTILIZ";   //  N    11,0
    public static final String DF_NAME_USE_DATA        = "DATA";       //  D     8,0
    public static final String DF_NAME_USE_COMMAND     = "COMMAND";    //  N     1,0
    public static final String DF_NAME_USE_STATO       = "STATO";      //  N     1,0

    public static final String DF_NAME_STATO_TX_OW_V1    = "TX_OW_V1";      //  C     1
    public static final String DF_NAME_STATO_TX_OW_V2    = "TX_OW_V2";      //  C     1
    public static final String DF_NAME_STATO_TX_WS       = "TX_WS";         //  C     1

}
