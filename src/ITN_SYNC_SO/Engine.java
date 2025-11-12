/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ITN_SYNC_SO;

import java.util.Date;
import java.util.Vector;
import org.apache.log4j.Logger;
import ITN_SYNC_SO.db.CommandInterface;
import ITN_SYNC_SO.db.DbProperties;
import ITN_SYNC_SO.db.dbf.DBF_ow20_pars;
import ITN_SYNC_SO.db.dbf.FileDBF;
import ITN_SYNC_SO.db.rx.DbMsSqlTxRx;
import ITN_SYNC_SO.hl7.HL7MessageSender;
import ITN_SYNC_SO.util.MySqlDate;
import ITN_SYNC_SO.util.MySqlDateTime;
import ITN_SYNC_SO.ws.WsTxRx;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

/**
 *
 * @author d.saponaro
 */
public class Engine extends Thread implements CommandInterface {

    private Logger log = Logger.getLogger(Engine.class);

    private final DbProperties dbPropertiesMaster;
    private final DbProperties dbProperties;
    
    public Engine(DbProperties dbPropertiesMaster, DbProperties dbProperties) {
        this.dbPropertiesMaster = dbPropertiesMaster;
        this.dbProperties = dbProperties;
    }

    @Override
    public void run() {
        if (GlobalParameters.isHL7()) {
            runHl7MsgSync();
        } else {
            runShareTableSync(dbPropertiesMaster, dbProperties);
        }
    }

    private void runShareTableSync(DbProperties dbPropertiesMaster, DbProperties dbProperties) {
        log.info("*** Shared Tables Integration/Interfacing ***");
        while(true){
            try {

                if (dbPropertiesMaster.isDIGISTAT() || dbPropertiesMaster.isSIX()) {
                    synchTipoKitTx(dbPropertiesMaster);
                    log.info("sleep(5000) after synchTipoKitTx()");
                    sleep(5000);
                }

                synchTx(dbPropertiesMaster, dbProperties);
                log.info("sleep(5000) after synchTx()");
                sleep(5000);

                synchRx(dbPropertiesMaster, dbProperties);

                log.info("sleep(5000) after synchRx()");
                sleep(5000);    // lo sleep c'e' sempre

                /*
                /synchInterventi();
                log.info("sleep(5000) after synchInterventi()");
                sleep(5000);
                 */
            } catch (InterruptedException ex) {

            }
        }
    }

    private void runHl7MsgSync() {
        log.info("*** HL7 Messages Integration/Interfacing ***");
        while(true){
            try {

                synchHL7Tx();
                log.info("sleep(5000) after synchHL7Tx()");
                sleep(5000);

            } catch (InterruptedException ex) {

            }
        }
    }

    private void synchHL7Tx() {
        log.info("Running synchHL7Tx()");
        boolean eof = false;
        boolean flg_done = false;
        int numRecFound = 0;
        int numRecSent = 0;
        FileDBF dbf = new FileDBF();
        if (!dbf.use(GlobalParameters.getDbf_use_sync_name())) {
            log.error("Cannot find dbf file \"" + GlobalParameters.getDbf_use_sync_name() + "\"");
            System.exit(1);
        } else {
            HL7MessageSender HL7sender = new HL7MessageSender(GlobalParameters.getHl7IpAddress(), GlobalParameters.getHl7Port(), GlobalParameters.getHL7file());

            eof = !dbf.gofirstNotDeleted(true);

            while (!eof) {
                if (!dbf.isDeleted()) {
                    flg_done = false;

                    SynchHL7 synch = dbf.leggiRecnoItinerisHL7();
                    if (synch.getCommand() == DF_OPR_NEW) {
                        numRecFound++;
                        flg_done = HL7sender.excecuteSend(synch);
                        if (flg_done) {
                            numRecSent++;
                        }
                    }

                    if (flg_done) {
                        dbf.delete();
                        eof = !dbf.skip();
                    } else {
                        eof = true; // provoco uscita se il msg non riesce
                    }
                } else {
                    eof = !dbf.skip();
                }
            }

            log.info(numRecFound == 0 ? "Nothing To Send" : Integer.toString(numRecFound) + " Records Found, " + Integer.toString(numRecSent) + " Records Sent");

            dbf.close();
        }
        
    }
    
    private void synchRx(DbProperties dbPropertiesMaster, DbProperties dbProperties) {
        if (dbPropertiesMaster.isDbRx_params()) {
            synchReceive(dbPropertiesMaster);
        }
        if (dbProperties != null && dbProperties.isDbRx_params()) {
            synchReceive(dbProperties);
        }        
    }
    
    private void synchTx(DbProperties dbPropertiesMaster, DbProperties dbProperties) {
        log.info("Running synchTx()");
        boolean eof = false;
        int numRecFound = 0;
        int numRecSent = 0;
        FileDBF dbf = new FileDBF();
        SynchAutority synchAut = new SynchAutority(dbPropertiesMaster, dbProperties, dbf);
        if (!dbf.use(GlobalParameters.getDbf_tra_sync_name())) {
            log.error("Cannot find dbf file \"" + GlobalParameters.getDbf_tra_sync_name() + "\"");
            System.exit(1);
        } else {
            dbf.gotop();
            while (!eof) {
                if (!dbf.isDeleted()) {

                    Synch synch = dbf.leggiRecnoItineris();
                    
                    if(synchAut.isTxToDb_Master()) {
                        if(syncTransmit(dbPropertiesMaster, synch))
                            dbf.updateTxDb_Master();
                    }
                    
                    if(synchAut.isTxToDb_2()) {
//                    if (dbProperties != null && synch.isTxToDb_2()) {
                        if(syncTransmit(dbProperties, synch))
                            dbf.updateTxDb_2();
                    }
                                        
                    if (synchAut.isTxToWs()) {
                        WsTxRx wsTxRx = new WsTxRx(dbPropertiesMaster);
                        if(wsTxRx.excecuteUpdate(synch))
                            dbf.updateTxWs();
                    }
                    
                    if(synchAut.isDeletable()) {
                        dbf.delete();
                    }

                }
                eof = !dbf.skip();
            }

            log.info(numRecFound == 0 ? "Nothing To Send" : Integer.toString(numRecFound) + " Records Found, " + Integer.toString(numRecSent) + " Records Sent");

            dbf.close();
        }
    }

    private boolean syncTransmit(DbProperties dbProperties, Synch synch){
        boolean ret = false;
        int numRecFound = 0;
        int numRecSent = 0;
        
        if (synch.getCommand() == DF_OPR_NEW
                || synch.getCommand() == DF_OPR_DEL && (dbProperties.isORMAWIN() || dbProperties.isORMAWIN_V2() || dbProperties.isSIX())) {
            numRecFound++;

            DbMsSqlTxRx updInterface = new DbMsSqlTxRx(dbProperties);

            if (updInterface.getNewConnection()) {
                if(ret = (updInterface.excecuteUpdate(synch))){
                    numRecSent++;
                }
                updInterface.shutdown();
            } else {
                log.error("Cannot connect to mssql server");
            }
        } else if (synch.getCommand() == DF_OPR_REFRESH_DEST) {
            ret = true;
        }

        log.info(numRecFound == 0 ? "Nothing To Send" : Integer.toString(numRecFound) + " Records Found, " + Integer.toString(numRecSent) + " Records Sent");

        return ret;
    }
    
    private void synchReceive(DbProperties dbProperties) {
        int numRecAdded = 0;

        DBF_ow20_pars dbf_lastDateTime = new DBF_ow20_pars(dbProperties);
        Date lastDateTime = dbf_lastDateTime.openDateTime();
        
        DbMsSqlTxRx mssql = new DbMsSqlTxRx(dbProperties);
        if (mssql.getNewConnection()) {
            Vector vtSynch = mssql.excecuteList(MySqlDateTime.javaToDb(lastDateTime, MySqlDate.df_mssql_db));
            if (vtSynch != null && vtSynch.size() > 0) {
                numRecAdded = elabKit(dbProperties, dbf_lastDateTime, vtSynch, lastDateTime, mssql);
            }

            log.info(numRecAdded == 0 ? "Nothing To Receive" : Integer.toString(numRecAdded) + " Records Received");

            mssql.shutdown();
        }
        
        dbf_lastDateTime.closeDateTime();
    }

    private int elabKit(DbProperties dbProperties, DBF_ow20_pars dbf_lastDateTime, Vector vtSynch, Date lastDateTime, DbMsSqlTxRx mssql) {
        int counter = 0;
        boolean tFound = false;
        Synch synch;
        FileDBF dbf = new FileDBF();

        //DBF_ow20_pars dbf_lastDateTime = new DBF_ow20_pars(dbProperties);

        if (!dbf.use(GlobalParameters.getDbf_tra_sync_name())) {
            log.error("Cannot find dbf file \"" + GlobalParameters.getDbf_tra_sync_name() + "\"");
            System.exit(1);
        } else {
            for (int i = 0; i < vtSynch.size(); i++) {
                tFound = false;
                synch = (Synch) vtSynch.get(i);

                String appoDateTime = MySqlDateTime.javaToLastDateTimeString(synch.getDateTime());
                String appoLastDateTime = MySqlDateTime.javaToLastDateTimeString(lastDateTime);
                if (appoDateTime.equals(appoLastDateTime)) {
                    tFound = seekSpedizione(dbf, appoDateTime, appoLastDateTime, synch);
                }

                if (!tFound) {
                    // mssql.execListInterventi(synch);    // richiesta interventi associati: non utilizzata. Comunque andrebbe messa in differita
                    if (dbf.append()) {
                        dbf.write(FileDBF.DF_NAME_COMMAND, Integer.toString(DF_OPR_FROM_SALA));
                        dbf.write(FileDBF.DF_NAME_IDSPEDIZ, Integer.toString(synch.getIdSpedizione()));
                        dbf.write(FileDBF.DF_NAME_ID_KIT, synch.getIdKit());
                        dbf.write(FileDBF.DF_NAME_USE_FLG, synch.getUseFlg());
                        dbf.write(FileDBF.DF_NAME_USE_SALA, synch.getUseSalaCode());
                        //dbf.write(FileDBF.DF_NAME_USE_SALADE, synch.getUseSalaDesc());
                        dbf.write(FileDBF.DF_NAME_USE_SALADE, synch.getDatiGeneraliIntervento());
                        dbf.write(FileDBF.DF_NAME_USE_IDINT, synch.getUseIdInt());
                        dbf.write(FileDBF.DF_NAME_USE_TIINT, synch.getUseTiInt());
                        dbf.write(FileDBF.DF_NAME_USE_ORA, synch.getUseOra());
                        dbf.write(FileDBF.DF_NAME_DATETIME, appoDateTime);
                        dbf.commit();
                        counter++;
                    }
                    if(dbf_lastDateTime.updDateTime(appoDateTime))
                        log.info("elabKit data aggiornata con " + appoDateTime + " su file " + Paths.get(dbProperties.getDbf_params_name()).normalize().toFile().getAbsolutePath());
                    else
                        log.error("elabKit data non aggiornata con " + appoDateTime + " su file " + Paths.get(dbProperties.getDbf_params_name()).normalize().toFile().getAbsolutePath());
                }
            }
        }
        dbf.close();

        return (counter);
    }

    private void synchTipoKitTx(DbProperties dbProperties) {
        log.info("Running synchTipoKitTx()");
        boolean eof = false;
        boolean flg_done = false;
        int numRecFound = 0;
        int numRecSent = 0;
        FileDBF dbf = new FileDBF();
        if (!dbf.use(GlobalParameters.getDbf_tkt_sync_name())) {
            log.error("Cannot find dbf file \"" + GlobalParameters.getDbf_tkt_sync_name() + "\"");
            System.exit(1);
        } else {
            DbMsSqlTxRx mssql = new DbMsSqlTxRx(dbProperties);
            if (mssql.getNewConnection()) {
                dbf.gotop();
                while (!eof) {
                    if (!dbf.isDeleted()) {

                        flg_done = false;
                        SynchTkit synch = dbf.leggiRecnoTipoKit();
                        if (synch.getCommand() == DF_OPR_NEW
                                || synch.getCommand() == DF_OPR_UPD
                                || synch.getCommand() == DF_OPR_DEL) {
                            numRecFound++;
                            flg_done = mssql.excecuteUpdate(synch);
                            if (flg_done) {
                                numRecSent++;
                            }
                        }

                        if (flg_done) {
                            dbf.delete();
                        }

                    }
                    eof = !dbf.skip();
                }
                mssql.shutdown();

                log.info(numRecFound == 0 ? "Nothing To Send" : Integer.toString(numRecFound) + " Records Found, " + Integer.toString(numRecSent) + " Records Sent");
            } else {
                log.error("Cannot connect to mssql server");
            }
            dbf.close();
        }
    }

    /*
    private void synchInterventi {
        boolean tFound = false;
        boolean eof = false;
        boolean loop = true;
        Synch dbfSynch;

        eof = !dbf.gotop();
        while(!tFound && !eof && loop) {
            dbfSynch = dbf.leggiRecnoOrmawin();
            if(loop = (lastDateTime.equals(lastDateTimeString))) {
                if(dbfSynch.getCommand() == DF_OPR_FROM_SALA &&
                   dbfSynch.getIdSpedizione() == synch.getIdSpedizione() &&
                   dbfSynch.getIdKit().equals(synch.getIdKit()))
                    tFound = true;
                else
                    eof = !dbf.skip();
            }
        }

        return(tFound);

        mssql.execListInterventi(synch);    // richiesta interventi associati
    }
     */
    private boolean seekSpedizione(FileDBF dbf, String lastDateTime, String lastDateTimeString, Synch synch) {
        boolean tFound = false;
        boolean eof = false;
        boolean loop = true;
        Synch dbfSynch;

        eof = !dbf.gotop();
        while (!tFound && !eof && loop) {
            if (loop = (lastDateTime.equals(lastDateTimeString))) {
                dbfSynch = dbf.leggiRecnoOrmawin();
                if (dbfSynch.getCommand() == DF_OPR_FROM_SALA
                        && dbfSynch.getIdSpedizione() == synch.getIdSpedizione()
                        && dbfSynch.getIdKit().equals(synch.getIdKit())) {
                    tFound = true;
                } else {
                    eof = !dbf.skip();
                }
            }
        }

        return (tFound);
    }

}
