/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO.db.rx;

import ITN_SYNC_SO.db.DbProperties;

/**
 *
 * @author root
 */
public abstract class DbMsSqlInterface {
    protected final int df_proc_view_interventi   = 1;
    protected final int df_proc_view_sale         = 2;
    protected final int df_proc_view_setFerri     = 3;

    protected final String tableScambioOW2K;
    protected final String viewIntervento;
    protected final String viewSala;
    protected final String viewSetFerri;

    protected final String tableScambioDIGI;
    protected final String tableTipoKit;

    protected final String tableScambioSIX;
    protected final String tableTipoKitSIX;
    protected final String tableReceiveSIX;
    
    protected final String DF_FLD_SCAMB_IDINT;
    protected final String DF_FLD_SCAMB_IDKIT;
    protected final String DF_FLD_VKIT_IDINT;
    protected final String DF_FLD_VKIT_IDKIT;
    
    protected final String DF_FLD_VSALA_IDINT;


    protected final String DF_NAME_ID_SPEDIZIONE      = "id_spedizione";
    protected final String DF_NAME_DATA               = "data";
    protected final String DF_NAME_COD_KIT            = "cod_kit";
    protected final String DF_NAME_COD_KIT_NUM        = "cod_kit_num";
    protected final String DF_NAME_DESCR_KIT          = "descr_kit";
    protected final String DF_NAME_ID_KIT             = "id_kit";
    protected final String DF_NAME_ID_KIT_NUM         = "id_kit_num";
    protected final String DF_NAME_DATA_SCAD          = "data_scad";
    protected final String DF_NAME_FL_USATO           = "fl_usato";
    protected final String DF_NAME_LOTTO              = "lotto";
    protected final String DF_NAME_STATO              = "stato";
    protected final String DF_NAME_ID_INTERVENTO      = "id_intervento";
    protected final String DF_NAME_LAST_UPD_ORMAWIN   = "last_upd_ormawin";
    protected final String DF_NAME_LAST_UPD_ITINERIS  = "last_upd_itineris";
    // usate solo da SIX
    protected final String DF_NAME_DATA_SCAD_SIX      = "data_scadenza";
    protected final String DF_NAME_FLAG_ANNULLO       = "flag_annullo";
    protected final String DF_NAME_NUMERO_DDT         = "numero";

    // usate solo da DIGISTAT
    protected final String DF_NAME_SALA_CODE          = "so_code";
    protected final String DF_NAME_SALA_DESC          = "so_descr";
    protected final String DF_NAME_SALA_DATAORA       = "dataorains";
    protected final String DF_NAME_SALA_SIGLAOP       = "sigla";
    protected final String DF_NAME_LAST_UPD_EXTERN    = "last_upd_extern";

    // Tabella Sync TipoKit
 // protected final String DF_NAME_COD_KIT            = "cod_kit";      vedi sopra
 // protected final String DF_NAME_COD_KIT_NUM        = "cod_kit_num";  vedi sopra
 // protected final String DF_NAME_DESCR_KIT          = "descr_kit";    vedi sopra
    protected final String DF_NAME_DATA_REV           = "data_rev";
    protected final String DF_NAME_TIPOLOGIA          = "tipologia";
    // usate per SIX
    protected final String DF_NAME_COD_KIT_ITN        = "cod_kit_itn";   // "cod_kit"
    protected final String DF_NAME_TIMESTAMP_ITN      = "timestamp_itn"; // "last_upd_itineris"



    protected final String DF_FLD_VIEW_IDINT      = "IDinterventi";

    protected final String DF_FLD_VSALA_DATA_INT  = "DataIntervento";
    protected final String DF_FLD_VSALA_CODE      = "CodSala";
    protected final String DF_FLD_VSALA_DESC      = "DescrizioneSala";
    protected final String DF_FLD_VSALA_CODE_BLOC = "CodBlocco";
    protected final String DF_FLD_VSALA_DESC_BLOC = "DescrizioneBlocco";
    protected final String DF_FLD_VSALA_DESC_REP  = "DescrizioneReparto";

    protected final String DF_FLD_VICD_CODE       = "ICD";
    protected final String DF_FLD_VICD_DESC       = "DescrizioneICD";

    protected final String DF_FLD_VKIT_ID         = "CodSetFerri";
    protected final String DF_FLD_VKIT_SIGLOPER   = "SiglaIns";
    protected final String DF_FLD_VKIT_DATETIME   = "DataOraIns";


    protected final String DF_FLD_SALA_CODE_V2      = "CodSala";
    protected final String DF_FLD_SALA_DESC_V2      = "DescrSala";
    
    protected final DbProperties dbProperties;

    public DbMsSqlInterface(DbProperties dbProperties) {
        this.dbProperties = dbProperties;
        
        if(dbProperties.isORMAWIN_V2())
            tableScambioOW2K     = dbProperties.getMssqlDbUsername() + "scambio_itineris_picasso";
        else
            tableScambioOW2K     = dbProperties.getMssqlDbUsername() + "scambio_itineris";
        
        
        viewIntervento       = dbProperties.getMssqlDbUsername() + "vPROC_ICD9CM";
        viewSala             = dbProperties.getMssqlDbUsername() + dbProperties.getMssqlDbViewSale("vSALE");
        viewSetFerri         = dbProperties.getMssqlDbUsername() + "vSETFERRI";

        tableScambioDIGI     = dbProperties.getMssqlDbUsername() + "Scambio";
        tableTipoKit         = dbProperties.getMssqlDbUsername() + "AnagraficaProdotti";

        tableScambioSIX     = dbProperties.getMssqlDbUsername() + "CONSEGNA_IPP";
        tableTipoKitSIX     = dbProperties.getMssqlDbUsername() + "ANAGRAFICA_KIT";
        tableReceiveSIX     = dbProperties.getMssqlDbUsername() + "CONSEGNA_SIX";
        
        DF_FLD_SCAMB_IDINT     = tableScambioOW2K + "." + DF_NAME_ID_INTERVENTO;
        DF_FLD_SCAMB_IDKIT     = tableScambioOW2K + "." + DF_NAME_ID_KIT;
        
        DF_FLD_VSALA_IDINT     = viewSala + ".IDinterventi";
        
        DF_FLD_VKIT_IDINT      = viewSetFerri + ".IDinterventi";
        DF_FLD_VKIT_IDKIT      = viewSetFerri + "." + DF_FLD_VKIT_ID;

        
    }
    
    
    
}
