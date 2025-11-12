
//Title:        Itineris Versione Lavin - UpLoad su AS400 
//Version:      
//Copyright:    Copyright (c) 2002
//Author:       Lucio
//Company:      P Studio Italia Srl
//Description:  Your description
package ITN_SYNC_SO.db.dbf;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.xBaseJ.DBF;
import org.xBaseJ.xBaseJException;
import ITN_SYNC_SO.Synch;
import ITN_SYNC_SO.SynchHL7;
import ITN_SYNC_SO.SynchTkit;
import java.nio.file.Paths;

public class FileDBF extends Object implements DBF_interface {

    private DBF aDBF;
    private boolean status_open = false;

    private Logger log = Logger.getLogger(FileDBF.class);

    public FileDBF() {
    }

    public boolean use(String dbfName) {
        try {
            aDBF = new DBF(dbfName);
            status_open = true;
            log.info("Open DBF, file=" + Paths.get(dbfName).normalize().toFile().getAbsolutePath());
        } catch (Exception e1) {
            log.error("Open Error " + dbfName, e1);
            status_open = false;
        }
        return (status_open);
    }

    public void close() {
        try {
            aDBF.close();
            log.info("Close DBF " + aDBF.getName());
        } catch (Exception e1) {
            log.error("Close Error", e1);            
        }
        status_open = false;
    }

    public boolean isOpen() {
        return(status_open);
    }

    public int recno() {
        return (aDBF.getCurrentRecordNumber());
    }

    public boolean gotoRecno(int recno) {
        boolean ret = false;
        try {
            aDBF.gotoRecord(recno);
            ret = true;
        } catch (xBaseJException e2) {
            log.error("Exception gotoRecno", e2);
        } catch (Exception e1) {
            log.error("Exception gotoRecno", e1);
        }
        if (ret) {
            ret = skip(1);
        }

        return (ret);
    }

    public boolean gotop() {
        boolean ret = false;
        try {
            aDBF.startTop();
            ret = true;
        } catch (xBaseJException e2) {
            log.error("Exception gotop", e2);
        } catch (Exception e1) {
            log.error("Exception gotop", e1);
        }
        if (ret) {
            ret = skip(1);
        }

        return (ret);
    }

    public boolean gobottom() {
        boolean ret = false;
        try {
            aDBF.startBottom();
            ret = true;
        } catch (xBaseJException e2) {
            log.error("Exception gobottom", e2);
        } catch (Exception e1) {
            log.error("Exception gobottom", e1);
        }
        if (ret) {
            ret = skip(-1);
        }

        return (ret);
    }

    public boolean skip() {
        return (skipNext(1));
    }

    public boolean skip(int num) {
        if(num >= 0)
            return(skipNext(num));
        else
            return(skipPrev(-num));
    }

    public boolean skipNext(int num) {
        boolean eof = false;
        int counter = 0;

        while (counter++ < num && !eof) {
            try {
                aDBF.read();
            } catch (xBaseJException e2) {
                eof = true;
            } catch (Exception e1) {
                log.error("Exception skipNext", e1);
                eof = true;
            }
        }
        return (!eof);
    }

    public boolean skipPrev(int num) {
        boolean bof = false;
        int counter = 0;

        while (counter++ < num && !bof) {
            try {
                aDBF.readPrev();
            } catch (xBaseJException e2) {
                bof = true;
            } catch (Exception e1) {
                log.error("Exception skipPrev", e1);
                bof = true;
            }
        }
        return (!bof);
    }

    public boolean write(int recno, String field, String value) {
        boolean ret = false;
        try {
            aDBF.gotoRecord(recno);
            aDBF.getField(field).put(value);
            ret = write(field, value);
        } catch (Exception e1) {
            log.error("Exception write", e1);
        }
        return(ret);
    }

    public boolean write(String field, String value) {
        boolean ret = false;
        try {
            aDBF.getField(field).put(value);
            ret = true;
        } catch (Exception e1) {
            log.error("Exception write", e1);
        }
        return(ret);
    }

    public String readStrTrim(String field) {
        return(readString(field).trim());
    }

    public String readString(String field) {
        String ret = "";
        try {
            ret = aDBF.getField(field).get();
        } catch (Exception e1) {
            log.error("Exception readString", e1);
            ret = "";
        }
        return(ret);
    }

    public void commit(){
        try {
            aDBF.update();
        } catch (xBaseJException ex) {
            log.error("Exception commit", ex);
        } catch (IOException ex) {
            log.error("Exception commit", ex);
        }
    }

    public void delete(int recno) {
        try {
            aDBF.gotoRecord(recno);
            aDBF.delete();
        } catch (Exception e1) {
            log.error("Exception delete", e1);
        }
    }

    public void delete() {
        try {
            aDBF.delete();
        } catch (Exception e1) {
            log.error("Exception delete", e1);
        }
    }

    public boolean isDeleted() {
        try {
            return(aDBF.deleted());
        } catch (Exception e1) {
            log.error("Exception isDeleted", e1);
            return(false);
        }
    }

    public void setOrder(){

    }

    public void seek(String value){
        
    }

    public boolean append(){
        try{
            aDBF.write();
            return true;
        } catch (Exception e1) {
            log.error("Exception append", e1);
        }
        return false;
    }

    public Synch leggiRecnoItineris() {
        Synch synch = new Synch();

        try {
            // Itineris fields
            synch.setCommand(aDBF.getField(DF_NAME_COMMAND).get());

            synch.setIdSpedizione(aDBF.getField(DF_NAME_IDSPEDIZ).get());
            synch.setData(aDBF.getField(DF_NAME_DATA).get().trim());
            synch.setCodiceKit(aDBF.getField(DF_NAME_COD_KIT).get().trim());
            synch.setCodiceKitNum(aDBF.getField(DF_NAME_COD_KIT_NUM).get().trim());
            synch.setDescKit(aDBF.getField(DF_NAME_DESC_KIT).get().trim());
            synch.setIdKit(aDBF.getField(DF_NAME_ID_KIT).get().trim());
            synch.setIdKitNum(aDBF.getField(DF_NAME_ID_KIT_NUM).get().trim());
            synch.setDataScad(aDBF.getField(DF_NAME_DATA_SCAD).get().trim());
            synch.setDestCode(aDBF.getField(DF_NAME_DEST_CODE).get().trim()); // non utilizzato in ormawin
            synch.setLotto(aDBF.getField(DF_NAME_LOTTO).get().trim());
            synch.setNumeroDDT(aDBF.getField(DF_NAME_NUM_DDT).get().trim());
        } catch (Exception e1) {
            log.error("Exception leggiRecnoItineris", e1);
            return null;
        }
        return synch;
    }
    
    public boolean isTxUpdated_Master() {
        return isTxUpdated(DF_NAME_STATO_TX_OW_V1);
    }
    public boolean isTxUpdated_2() {
        return isTxUpdated(DF_NAME_STATO_TX_OW_V2);
    }

    public boolean isTxUpdatedWs() {
        return isTxUpdated(DF_NAME_STATO_TX_WS);
    }
    
    private boolean isTxUpdated(String fieldname) {
        return readStrTrim(fieldname).equals("1");
    }
    
    public void updateTxDb_Master() {
        updateTxDb(DF_NAME_STATO_TX_OW_V1);
    }
    public void updateTxDb_2() {
        updateTxDb(DF_NAME_STATO_TX_OW_V2);
    }
    public void updateTxWs() {
        updateTxDb(DF_NAME_STATO_TX_WS);
    }
    
    private void updateTxDb(String fieldname) {
        if(write(fieldname, "1"))
            commit();
    }
    
    public Synch leggiRecnoOrmawin() {
        Synch synch = new Synch();

        try {
            // Ormawin fields
            synch.setIdSpedizione(Integer.parseInt(aDBF.getField(DF_NAME_IDSPEDIZ).get().trim()));
            synch.setIdKit(aDBF.getField(DF_NAME_ID_KIT).get().trim());
            synch.setCommand(Integer.parseInt(aDBF.getField(DF_NAME_COMMAND).get().trim()));

            synch.setUseFlg(aDBF.getField(DF_NAME_USE_FLG).get());
            synch.setUseIdInt(aDBF.getField(DF_NAME_USE_IDINT).get());
            synch.setUseOra(aDBF.getField(DF_NAME_USE_ORA).get());
            synch.setUseSalaCode(aDBF.getField(DF_NAME_USE_SALA).get());
            synch.setUseSalaDesc(aDBF.getField(DF_NAME_USE_SALADE).get());
            synch.setUseTiInt(aDBF.getField(DF_NAME_USE_TIINT).get());
        } catch (Exception e1) {
            log.error("Exception leggiRecnoOrmawin", e1);
            return null;
        }
        return synch;
    }

    public SynchTkit leggiRecnoTipoKit() {
        SynchTkit synch = new SynchTkit();

        try {
            // TipoKit fields
            synch.setCommand(aDBF.getField(DF_NAME_TKIT_COMMAND).get());

            synch.setCodiceKit(aDBF.getField(DF_NAME_TKIT_COD_KIT).get().trim());
            synch.setCodiceKitNum(aDBF.getField(DF_NAME_TKIT_COD_KIT_NUM).get().trim());
            synch.setDescKit(aDBF.getField(DF_NAME_TKIT_DESC_KIT).get().trim());
            synch.setDataRev(aDBF.getField(DF_NAME_TKIT_DATAREV).get().trim());
            synch.setTipo(aDBF.getField(DF_NAME_TKIT_TIPO).get().trim());
        } catch (Exception e1) {
            log.error("Exception leggiRecnoTipoKit", e1);
            return null;
        }
        return synch;
    }

    public SynchHL7 leggiRecnoItinerisHL7() {
        SynchHL7 synch = new SynchHL7();

        try {
            // Itineris fields
            synch.setCommand(aDBF.getField(DF_NAME_USE_COMMAND).get());
            synch.setIdUtilizzo(aDBF.getField(DF_NAME_USE_IDUTILIZ).get());

            for( int i = 1; i <= aDBF.getFieldCount(); i++ ) {
                synch.addFieldValue(aDBF.getField(i).getName(), aDBF.getField(i).get().trim());
            }

        } catch (Exception e1) {
            log.error("Exception leggiRecnoItinerisHL7", e1);
            return null;
        }
        return synch;
    }

    private String getStringField(String fieldName) {
        try {
            return(aDBF.getField(fieldName).get());
        } catch (xBaseJException ex) {
            log.error("Exception getStringField", ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            log.error("Exception getStringField", ex);
        }
        return("");
    }

    public boolean gofirstNotDeleted(boolean fromBottom) {
        boolean flg_skipped = false;
        boolean bof, eof = true;

        if(fromBottom) {
            bof = !this.gobottom();
            while(!bof && !this.isDeleted()) {
                bof = !this.skip(-1);
                flg_skipped = true;
            }
            if(flg_skipped && (bof || this.isDeleted()))
                eof = !this.skip(1);
        }
        else {
            eof = !this.gotop();
            while(!eof && this.isDeleted())
                eof = !this.skip(1);
        }

        return(!eof);
    }

} 