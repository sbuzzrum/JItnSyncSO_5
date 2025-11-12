/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO;

import java.util.Hashtable;

/**
 *
 * @author root
 */
public class SynchHL7 {

    private int       command      = 0;
    private int       idUtilizzo   = 0;
    private String    data         = "";
    private Hashtable dbFieldValue = new Hashtable();
    
    /**
     * @param idUtilizzo the idUtilizzo to set
     */
    public void setIdUtilizzoe(int idUtilizzo) {
        this.idUtilizzo = idUtilizzo;
    }

    public void setIdUtilizzo(String idUtilizzo) {
        this.idUtilizzo = getInt(idUtilizzo);
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
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

    public String getFieldValue(String pFieldName) {
        String ret = "";
        try {
            if( (ret = (String) dbFieldValue.get(pFieldName.toUpperCase())) == null)
                ret = "";
        } catch(Exception e) {
            ret = "";
        }
        return(ret);
    }

    public void addFieldValue(String pFieldName, String pValue) {
        dbFieldValue.put(pFieldName.toUpperCase(), pValue);
    }

    public void addFieldValue(String pFieldName, int pValue) {
        dbFieldValue.put(pFieldName.toUpperCase(), Integer.toString(pValue));
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

}
