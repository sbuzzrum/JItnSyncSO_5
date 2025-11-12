package ITN_SYNC_SO.hl7;

import ITN_SYNC_SO.SynchHL7;
import ITN_SYNC_SO.util.MyDate;
import ITN_SYNC_SO.util.MyDateUtil;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import java.io.*;
import org.apache.log4j.Logger;

/**
 *
 * @author d.saponaro
 */
public class HL7MessageSender {

    private Logger logger = Logger.getLogger(HL7MessageSender.class); 

    private final String df_tag_dbfield_beg = "<DBF>";
    private final String df_tag_dbfield_end = "</DBF>";
    private final String df_tag_datetime    = "<DATETIME/>";
    private final String df_tag_seconds     = "<SECONDS/>";

    String address;
    int port;
    String patternFile;

    public HL7MessageSender(String address, String port, String patternFile) {
        this.address     = address;
        this.port        = Integer.parseInt(port);
        this.patternFile = patternFile;
    }

    public boolean excecuteSend(SynchHL7 synch) {
        boolean ret = false;
        String msg;

        File file = new File(patternFile);
        if( !file.exists())
            logger.error("Pattern File doesn't exist: " + patternFile);
        else {
            if( (msg = elabFile(file, synch)) != null )
                ret = sendMsg(msg);
        }
        return(ret);
    }


 /*   public void testMessage(String address, int port, String msg){
        PropertyConfigurator.configure(clientLoggerFileName);      
        try{
            if(msg == null){
                msg = "MSH|^~\\&|VITRUVIO|VITRUVIO|||201510091353|SEC|ADT^A04|33338BDA9B2|P|2.3.1|||NE|AL\r"
                    + "EVN|A04|201510091353\r"
                    + "PID|||VIT0000A30891||TEST^PT1||19580404|M|||VIA OLANDA N. 56^^FAVARA^^084017||(444)999-3333|||M||REQ00005A161^^^Visit number|||||FAVARA|||I\r"
                    + "PV1||M|Visiting^HOLD^1^ISM|||||||||||||||A|REQ00005A161^^^Visit number||SSN|SSN||||||||||||||||||||||201510091353";
                
                
                logger.info("Send example message " + msg);
            }
            else{
                File file = new File(msg);
                if(file.exists()){
                    String tmpMsg = elabFile(file);
                    if(tmpMsg != null){
                        msg = tmpMsg;
                        logger.info("Send message from file " + msg);
                    }
                }
                else
                    logger.info("Send message from command line: " + msg);
            }
            
            HapiContext context = new DefaultHapiContext();
            Parser p = context.getPipeParser();
            
            logger.info("Parsing message...");
            Message adt = p.parse(msg);
            logger.info("Message parsed");
            
            logger.info("Connectin to server at " + address + ":" + port + " ...");
            Connection connection = context.newClient(address, port, false);
            logger.info("Connected");
            
            Initiator initiator = connection.getInitiator();
            
            logger.info("Sending message and waiting for response...");
            Message response = initiator.sendAndReceive(adt);

            String responseString = p.encode(response);
            logger.info("Received response: " + responseString);
            
            
            logger.info("Closing connection...");
            connection.close();
            logger.info("Connection closed");
            
        }catch(HL7Exception e){
            logger.error("Error HL7Exception", e);
            logger.error("Probabilmente il server non è in ascolto");
        }catch(LLPException e){
            logger.error("Error LLPException", e);
        }catch(IOException e){
            logger.error("Error IOException", e);
        } 
        
        logger.info("Done, exiting");
        System.exit(0);
    } */

    private boolean sendMsg(String msg) {
        boolean ret = false;

        try{

            logger.info("Message to send...\n" + msg);

            HapiContext context = new DefaultHapiContext();
            Parser p = context.getPipeParser();

            logger.info("Parsing message...");
            Message oru = p.parse(msg);
            logger.info("Message parsed");

            logger.info("Connectin to server at " + address + ":" + port + " ...");
            Connection connection = context.newClient(address, port, false);
            logger.info("Connected");

            Initiator initiator = connection.getInitiator();

            logger.info("Sending message and waiting for response...");
            Message response = initiator.sendAndReceive(oru);

            String responseString = p.encode(response);
            logger.info("Received response: " + responseString);


            logger.info("Closing connection...");
            connection.close();
            logger.info("Connection closed");

            ret=true;

        }catch(HL7Exception e){
            logger.error("Error HL7Exception", e);
            logger.error("Server not listening");
        }catch(LLPException e){
            logger.error("Error LLPException", e);
        }catch(IOException e){
            logger.error("Error IOException", e);
        }

        return(ret);
    }

    private String elabFile(File file, SynchHL7 synch) {
        StringBuffer ret = new StringBuffer();

        try {
            logger.info("Opening file " + file.getAbsolutePath());            
            RandomAccessFile raf = new RandomAccessFile(file, "r");            
            logger.info("Done, reading...");

            while(raf.getFilePointer() < raf.length())
                ret.append(elabRow(raf.readLine(), synch));

            logger.info("Done, closing file...");
            raf.close();
        } catch (FileNotFoundException ex) {
            logger.error("File not found!", ex);
            ret = null;
        } catch (IOException ex) {
            logger.error("IOException while reading file!", ex);
            ret = null;
        }
        return(ret.toString());
    }

    private String elabRow(String row, SynchHL7 synch) {
        StringBuffer ret = new StringBuffer(row);
        int posBeg, posEnd;
        boolean cicla = true;

        String datetime = MyDateUtil.getToday(MyDate.df_date_format_yyyyMMddHHmmss);

        // sostituisci valori <DBF>NOME-FIELD</DBF>
        while(cicla) {
            cicla = false;
            if( (posBeg = ret.indexOf(df_tag_dbfield_beg)) > 0) {
                if( (posEnd = ret.indexOf(df_tag_dbfield_end, posBeg + df_tag_dbfield_beg.length())) > 0) {
                    ret.replace(posBeg, posEnd + df_tag_dbfield_end.length(), synch.getFieldValue(ret.substring(posBeg + df_tag_dbfield_beg.length(), posEnd)));
                    cicla = true;
                }
            }
        }

        // sostituisci valori <DATETIME/>
        if( (posBeg = ret.indexOf(df_tag_datetime)) > 0)
            ret.replace(posBeg, posBeg + df_tag_datetime.length(), datetime);

        // sostituisci valori <SECONDS/>
        if( (posBeg = ret.indexOf(df_tag_seconds)) > 0)
            ret.replace(posBeg, posBeg + df_tag_seconds.length(), Long.toString(System.currentTimeMillis()));

        while(ret.charAt(ret.length() -1) == '\n')
            ret.deleteCharAt(ret.length() -1);

        if(ret.charAt(ret.length() -1) != '\r')
            ret.append('\r');

        return(ret.toString());
    }
}
