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

public class DbSqlUpdate implements DbSqlInterface {

  private String sql_left;
  private String whereCond = "";
  private boolean first = true;

  public DbSqlUpdate(String tableName) {
    sql_left  = new String("UPDATE " + tableName + " SET ");
  }

  public void addString(String nomeField, String valore) {
    this.setSeparator();
    if(valore != null)
        sql_left = sql_left.concat(nomeField + '=' + MyUtil.df_str_apice + MyUtil.addDoppioApice(valore) + MyUtil.df_str_apice);
    else    
        sql_left = sql_left.concat(nomeField + "=NULL");


  }

  public void addInt(String nomeField, int valore) {
    this.setSeparator();
    sql_left = sql_left.concat(nomeField + '=' + Integer.toString(valore));
  }

  public void addInt(String nomeField, String valueString, int valore) {
    this.setSeparator();
    sql_left = sql_left.concat(nomeField + '=' + valueString + Integer.toString(valore));
  }
  
  public void addFloat(String nomeField, float valore) {
    this.setSeparator();
    sql_left = sql_left.concat(nomeField + '=' + Float.toString(valore));
  }

  public void addLong(String nomeField, long valore) {
    this.setSeparator();
    sql_left = sql_left.concat(nomeField + '=' + Long.toString(valore));
  }
  
  public void addBool(String nomeField, boolean valore) {
    addString(nomeField, (valore ? "true" : "false"));
  }
    
  public void addDate(String nomeField, Date valore) {
    this.setSeparator();
    sql_left = sql_left.concat(nomeField + '=' + MySqlDate.javaToDb(valore));
  }

  public void addDate(String nomeField, Date valore, int tipo_db) {
    this.setSeparator();
    sql_left = sql_left.concat(nomeField + '=' + MySqlDate.javaToDb(valore, tipo_db));
  }

  public void addDateOracle(String nomeField, String valore) {
    this.setSeparator();
    sql_left = sql_left.concat(nomeField + "= to_date('" + valore + "','YYYYMMDD')");
  }

  public void addTimeStamp(String nomeField, Timestamp valore) {
    addString(nomeField, valore.toString());
  }

  public void addFunction(String nomeField, String nomeFunction) {
    this.setSeparator();
    sql_left = sql_left.concat(nomeField + '=' + nomeFunction);
  }
  public String getSql() {
    return(sql_left.concat(whereCond));
  }
  
  public void setWhereCondition(String _whereCond) {
      whereCond = new String(" WHERE " + _whereCond);
  }
  
  private void setSeparator() {
    if(!first) sql_left  = sql_left.concat(",");
    else first = false;
  }
}