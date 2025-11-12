/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ITN_SYNC_SO;

import ITN_SYNC_SO.db.DbProperties;
import ITN_SYNC_SO.db.dbf.FileDBF;

/**
 *
 * @author lucio
 */
public class SynchAutority {
    DbProperties dbPropertiesMaster;
    DbProperties dbProperties;
    FileDBF dbf;
            
    public SynchAutority(DbProperties dbPropertiesMaster, DbProperties dbProperties, FileDBF dbf) {
        this.dbPropertiesMaster = dbPropertiesMaster;
        this.dbProperties = dbProperties;
        this.dbf = dbf;
    }
    
    public boolean isTxToDb_Master() {
        return isTXmaster() && !dbf.isTxUpdated_Master();
    }

    public boolean isTxToDb_2() {
        return isTX2() && !dbf.isTxUpdated_2();
    }
    
    public boolean isTxToWs() {
        // Trasmetto WS solo se e' ORMAWIN_V2, e se previsto DbTX la tabella gia' aggiornata, e non e' aggiornato il record
        return isTXws() && (!isTXmaster() || dbf.isTxUpdated_Master()) &&  !dbf.isTxUpdatedWs();
    }

    public boolean isDeletable() {
        return (!isTXmaster() || dbf.isTxUpdated_Master()) && (!isTX2() || dbf.isTxUpdated_2()) && (!isTXws() || dbf.isTxUpdatedWs());    
    }
    
    private boolean isTXmaster() {
        return dbPropertiesMaster.isDbTx_params() && !dbPropertiesMaster.isORMAWIN_V2();
    }
    private boolean isTX2() {
        return dbProperties != null && dbProperties.isDbTx_params();
    }
    private boolean isTXws() {
        return dbPropertiesMaster.isORMAWIN_V2();
    }

}

