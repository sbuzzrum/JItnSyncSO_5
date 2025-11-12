package ITN_SYNC_SO.db;

//Title:        Production Managing System

import java.sql.Timestamp;
import java.util.Date;

//Version:      Ver. 1.01.01
//Copyright:    Copyright (c) 2002
//Author:       Minetti Lucio Mario
//Company:      Technosys p.s.c.a.r.l.
//Description:  DB Module - SQL insert command parser


public class DbSqlDelete implements DbSqlInterface {

  private String ret_sql;
  private String whereCond = "";

  public DbSqlDelete(String tableName) {
    ret_sql  = new String("DELETE FROM " + tableName + " ");
  }

  public String getSql() {
    return(ret_sql.concat(whereCond));
  }
  
  public void setWhereCondition(String _whereCond) {
      whereCond = new String(" WHERE " + _whereCond);
  }

    public void addString(String nomeField, String valore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addInt(String nomeField, int valore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addFloat(String nomeField, float valore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addLong(String nomeField, long valore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addBool(String nomeField, boolean valore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addDate(String nomeField, Date valore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addDateOracle(String nomeField, String valore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addTimeStamp(String nomeField, Timestamp valore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addFunction(String nomeField, String nomeFunction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
}