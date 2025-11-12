package ITN_SYNC_SO.db;

//Title:        Production Managing System
//Version:      Ver. 1.01.01
//Copyright:    Copyright (c) 2002
//Author:       Minetti Lucio Mario
//Company:      Technosys p.s.c.a.r.l.
//Description:  DB Module - SQL insert command parser


import ITN_SYNC_SO.util.*;
import java.sql.Timestamp;
import java.util.Date;

public class DbSqlInsert implements DbSqlInterface {

  private String sql_left;
  private String sql_right;
  private boolean first = true;

  public DbSqlInsert(String tableName) {
    sql_left  = new String("INSERT INTO " + tableName + " (");
    sql_right = new String(" VALUES(");
  }

  public void addString(String nomeField, String valore) {
    this.setSeparator();
    sql_left  = sql_left.concat(nomeField);
    if(valore != null)
        sql_right = sql_right.concat(MyUtil.df_str_apice + MyUtil.addDoppioApice(valore) + MyUtil.df_str_apice);
    else
        sql_right = sql_right.concat("NULL");
  }

  public void addInt(String nomeField, int valore) {
    this.setSeparator();
    sql_left  = sql_left.concat(nomeField);
    sql_right = sql_right.concat(Integer.toString(valore));
  }

  public void addFloat(String nomeField, float valore) {
    this.setSeparator();
    sql_left  = sql_left.concat(nomeField);
    sql_right = sql_right.concat(Float.toString(valore));
  }

  public void addLong(String nomeField, long valore) {
    this.setSeparator();
    sql_left  = sql_left.concat(nomeField);
    sql_right = sql_right.concat(Long.toString(valore));
  }
  
  public void addBool(String nomeField, boolean valore) {
    addString(nomeField, (valore ? "true" : "false"));
  }
  
  public void addDate(String nomeField, Date valore) {
    this.setSeparator();
    sql_left  = sql_left.concat(nomeField);
    sql_right = sql_right.concat(MySqlDate.javaToDb(valore));
  }

  public void addDate(String nomeField, Date valore, int tipo_db) {
    this.setSeparator();
    sql_left  = sql_left.concat(nomeField);
    sql_right = sql_right.concat(MySqlDate.javaToDb(valore, tipo_db));
  }

  public void addDateOracle(String nomeField, String valore) {
    this.setSeparator();
    sql_left  = sql_left.concat(nomeField);
    sql_right = sql_right.concat("to_date('" + valore + "','YYYYMMDD')");
  }

  public void addTimeStamp(String nomeField, Timestamp valore) {
    addString(nomeField,  valore.toString());
  }

  public void addFunction(String nomeField, String nomeFunction) {
    this.setSeparator();
    sql_left  = sql_left.concat(nomeField);
    sql_right = sql_right.concat(nomeFunction);
  }

  public void setWhereCondition(String _whereCond) {
      // not used
  }
  
  public String getSql() {
    return(sql_left + ")" + sql_right + ")");
  }

  private void setSeparator() {
    if(!first) {
      sql_left  = sql_left.concat(",");
      sql_right = sql_right.concat(",");
    }
    else first = false;
  }
  
}