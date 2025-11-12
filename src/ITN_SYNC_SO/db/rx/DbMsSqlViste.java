/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO.db.rx;

import java.sql.ResultSet;
import java.sql.SQLException;
import ITN_SYNC_SO.Synch;
import ITN_SYNC_SO.db.DbProperties;
import ITN_SYNC_SO.util.MyDate;

/**
 *
 * @author root
 */
public class DbMsSqlViste extends DbMsSqlInterface {
    private int tipo_procedura = 0;
    private Synch synch;

    public DbMsSqlViste(DbProperties dbProperties, int procType, Synch synch) {
        super(dbProperties);
        tipo_procedura = procType;
        this.synch = synch;
    }

    public void getItem(ResultSet rs) throws SQLException{
        if(tipo_procedura == df_proc_view_interventi)
            getItemIntervento(rs, synch);
        else if(tipo_procedura == df_proc_view_sale)
            getItemSala(rs, synch);
        else if(tipo_procedura == df_proc_view_setFerri)
            getItemSetFerri(rs, synch);
    }

    public Synch getItemIntervento(ResultSet rs, Synch synch) throws SQLException{
        synch.addUseTiInt(rs.getString(DF_FLD_VICD_CODE), rs.getString(DF_FLD_VICD_DESC));
        return synch;
    }

    public Synch getItemSala(ResultSet rs, Synch synch) throws SQLException{
        synch.setUseSalaCode(rs.getString(DF_FLD_VSALA_CODE));
        synch.setUseSalaDesc(rs.getString(DF_FLD_VSALA_DESC));

        // Attenzione i campi sottostanti non sono presenti nella vista ne nel documento di specifiche
        /*
        synch.setUseCodeBlocco(rs.getString(DF_FLD_VSALA_CODE_BLOC));
        synch.setUseDescBlocco(rs.getString(DF_FLD_VSALA_DESC_BLOC));
        synch.setUseDescReparto(rs.getString(DF_FLD_VSALA_DESC_REP));
        synch.setUseDataInt(rs.getString(DF_FLD_VSALA_DATA_INT));
        */
        return synch;
    }

    public Synch getItemSetFerri(ResultSet rs, Synch synch) throws SQLException{
        synch.setUseOra(MyDate.toString(rs.getTimestamp(DF_FLD_VKIT_DATETIME), "yyyy-MM-dd HH:mm:ss"));
        return synch;
    }

    public String getSqlListInterventi(int IDintervento) {
        String sql = "SELECT * FROM " + viewIntervento +
                     " WHERE " + DF_FLD_VIEW_IDINT + "=" + Integer.toString(IDintervento);
        return sql;
    }

    public String getSqlListSale(int IDintervento) {
        String sql = "SELECT * FROM " + viewSala +
                     " WHERE " + DF_FLD_VIEW_IDINT + "=" + Integer.toString(IDintervento);
        return sql;
    }

    public String getSqlListSetFerri(int IDintervento, String IDkit) {
        String sql = "SELECT * FROM " + viewSetFerri +
                     " WHERE " + DF_FLD_VIEW_IDINT + "=" + Integer.toString(IDintervento) +
                     " AND " +  DF_FLD_VKIT_ID + " = '" + IDkit + "'";
        return sql;
    }

}
