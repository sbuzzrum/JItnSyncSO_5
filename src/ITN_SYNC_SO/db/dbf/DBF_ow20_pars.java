/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO.db.dbf;

import ITN_SYNC_SO.GlobalParameters;
import java.util.Date;
import org.apache.log4j.Logger;
import ITN_SYNC_SO.util.MySqlDateTime;
import ITN_SYNC_SO.db.DbProperties;
import ITN_SYNC_SO.db.dbf.FileDBF;


/**
 *
 * @author root
 */
public class DBF_ow20_pars {
    public final String DF_NAME_LASTDTIME   = "LAST_DTIME";//     C    14,0
    //public final String DF_NAME_PARS_DBF = System.getProperty("user.dir") + "/ow20_pars.dbf";

    private final String df_DataTime = "20090101010000";
    private final int df_close = 0;
    private final int df_open  = 1;

    private Logger log = Logger.getLogger(DBF_ow20_pars.class);

    private FileDBF dbf = null;
    private final DbProperties dbProperties;
    
    public DBF_ow20_pars(DbProperties dbProperties) {
        this.dbProperties = dbProperties;
    }
    
    private boolean isOpen(){
        boolean ret = false;
        if(dbf != null)
            ret = dbf.isOpen();
        return(ret);
    }

    public Date openDateTime(){
        String ret = df_DataTime;
        
        if(isOpen()) closeDateTime();
        
        dbf = new FileDBF();
        if(!dbf.use(dbProperties.getDbf_params_name())){
            log.error("Cannot find dbf file \"" + dbProperties.getDbf_params_name() + "\"");
            System.exit(1);
        }
        else {
            if(!dbf.gotop()) {
                dbf.append();
                dbf.commit();
            }
            if( dbf.readStrTrim(DF_NAME_LASTDTIME).length() > 0)
                ret = dbf.readStrTrim(DF_NAME_LASTDTIME);
        }
        return(MySqlDateTime.javaFromLastDateTimeString(ret));
    }

    public void closeDateTime(){
        dbf.close();
        dbf = null;
    }

    public boolean updDateTime(Date lastDateTime){
        return(updDateTime(MySqlDateTime.javaToLastDateTimeString(lastDateTime)));
    }

    public boolean updDateTime(String lastDateTime){
        boolean ret = false;

        if(isOpen()) {
            if(dbf.gotop()) {
                ret = dbf.write(DF_NAME_LASTDTIME, lastDateTime);
                dbf.commit();
                if(!ret)
                    log.error("Could not write!");
            } else
                log.error("Can not go top!");
        } else
            log.error("File is closed!");
        
        return(ret);
    }

}
