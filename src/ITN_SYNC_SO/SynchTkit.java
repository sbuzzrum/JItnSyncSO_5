/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO;

import java.util.Date;

/**
 *
 * @author d.saponaro
 */
public class SynchTkit {

    private int    command        = 0;
    private String codiceKit      = "";
    private int    codiceKitNum   = 0;
    private int    codiceGruNum   = 0;
    private String gruppo         = "";
    private String descKit        = "";
    private String dataRev        = "";
    private int    tipo           = 0;  // 1=Kit, 2=Imb Strumenti, 3=Imb Tessile
    private Date   dateTime       = null;


    /**
     * @return the codiceKit
     */
    public String getCodiceKit() {
        return codiceKit;
    }

    /**
     * @param codiceKit the codiceKit to set
     */
    public void setCodiceKit(String codiceKit) {
        this.codiceKit = codiceKit;
    }

    /**
     * @return the descKit
     */
    public String getDescKit() {
        return descKit;
    }

    /**
     * @param descKit the descKit to set
     */
    public void setDescKit(String descKit) {
        this.descKit = descKit;
    }

    /**
     * @return the command
     */
    public int getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(int command) {
        this.command = command;
    }

    public void setCommand(String command) {
        this.command = getInt(command);
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the dataRev
     */
    public String getDataRev() {
        return dataRev;
    }

    /**
     * @param data the dataRev to set
     */
    public void setDataRev(String dataRev) {
        this.dataRev = dataRev;
    }


    /**
     * @return the codiceKitNum
     */
    public int getCodiceKitNum() {
        return codiceKitNum;
    }

    /**
     * @param codiceKitNum the codiceKitNum to set
     */
    public void setCodiceKitNum(int codiceKitNum) {
        this.codiceKitNum = codiceKitNum;
    }
    public void setCodiceKitNum(String codiceKitNum) {
        this.codiceKitNum = (codiceKitNum.length() > 0 ? Integer.parseInt(codiceKitNum) : 0);
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = (tipo.length() > 0 ? Integer.parseInt(tipo) : 0);
    }


    private String getString(String pValue) {
        return(pValue != null ? pValue : "");
    }

    private String getString(String pValue, int lenght) {
        String ret = getString(pValue);
        return(ret.length() > 10 ? ret.substring(0,10) : ret);
    }

    private int getInt(String pValue) {
        try {
            return(Integer.parseInt((pValue != null ? pValue.trim() : "0")));
        }
        catch(Exception e) {
            return(0);
        }
    }

    /**
     * @return the gruppo
     */
    public String getGruppo() {
        return gruppo;
    }

    /**
     * @param gruppo the gruppo to set
     */
    public void setGruppo(String gruppo) {
        this.gruppo = gruppo;
    }

    /**
     * @return the codiceGruNum
     */
    public int getCodiceGruNum() {
        return codiceGruNum;
    }

    /**
     * @param codiceGruNum the codiceGruNum to set
     */
    public void setCodiceGruNum(String codiceGruNum) {
        this.codiceGruNum = (codiceGruNum.length() > 0 ? Integer.parseInt(codiceGruNum) : 0);
    }

}
