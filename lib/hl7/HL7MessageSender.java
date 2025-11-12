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
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author d.saponaro
 */
public class HL7MessageSender {
    
    private final String clientLoggerFileName = System.getProperty("user.dir") + "/client-logger.conf";
    
    private Logger logger = Logger.getLogger(HL7MessageSender.class); 
    
    public HL7MessageSender(){
        PropertyConfigurator.configure(clientLoggerFileName);  
    }
    
    public void testMessage(String address, int port, String msg){
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
    }
    
    private String elabFile(File file){
        String ret = "";
        try {
            logger.info("Opening file " + file.getAbsolutePath());            
            RandomAccessFile raf = new RandomAccessFile(file, "r");            
            logger.info("Done, reading...");
            while(raf.getFilePointer() < raf.length()){                
                ret += raf.readLine();
                if(ret.charAt(ret.length() -1) != '\r')
                    ret += '\r';
            }            
            logger.info("Done, closing file...");
            raf.close();
        } catch (FileNotFoundException ex) {
            logger.error("File not found!", ex);
            ret = null;
        } catch (IOException ex) {
            logger.error("IOException while reading file!", ex);
            ret = null;
        }
        return ret;
    }
    
}
