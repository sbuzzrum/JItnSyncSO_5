/*
 * PmsDbMainInterface.java
 *
 * Created on 3 maggio 2003, 13.56
 */

package ITN_SYNC_SO.db;

/**
 *
 * @author  Hal9154
 */

import java.sql.Timestamp;
import java.util.Date;

public interface DbSqlInterface {
    
    public void addString(String nomeField, String valore);
    public void addInt(String nomeField, int valore);
    public void addFloat(String nomeField, float valore);
    public void addLong(String nomeField, long valore);
    public void addBool(String nomeField, boolean valore);
    public void addDate(String nomeField, Date valore);
    public void addDateOracle(String nomeField, String valore);
    public void addTimeStamp(String nomeField, Timestamp valore);
    public void addFunction(String nomeField, String nomeFunction);
    public void setWhereCondition(String _whereCond);
    public String getSql();
    
}
