/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO;

import java.io.File;
import ITN_SYNC_SO.db.DbProperties;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

/**
 *
 * @author d.saponaro
 */
public class Main {

    private Logger logger = Logger.getRootLogger();
    
    private FileAppender newAppender = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        if(args.length > 1)
            new Main(args);
        else
            System.err.print("Errore nei parametri di lancio");
    }

    public Main(String[] args){
        GlobalParameters.readProperties(args[0]);
        
        configureLogger(GlobalParameters.getLogger_params_name(), null);
                    
        ArrayList<DbProperties> dbPropertiesList = new ArrayList<>();
        for(int i=1; i<args.length; i++){
            String arg = args[i];
            //System.getProperty("user.dir") + "/rdbms.prop"
            DbProperties dbProperties = new DbProperties();
            dbProperties.readDbProperties(arg);
            
            
            dbPropertiesList.add(dbProperties);
            // informazioni
            Level logLevel = logger.getLevel();
            logger.setLevel(Level.ALL);
            logger.info(dbProperties.showDbProperties());
            logger.setLevel(logLevel);
            // informazioni
        }
        
        Engine engine = null;
        
        if(dbPropertiesList.size() == 2)
            engine = new Engine(dbPropertiesList.get(0), dbPropertiesList.get(1));
        else
            engine = new Engine(dbPropertiesList.get(0), null);
                    
        engine.start();
    }

    private void configureLogger(String configName,  String prefix){
        //String configName = System.getProperty("user.dir")+ "/logger.conf";
        
        File file = new File(configName);
        if(file.exists()){
            PropertyConfigurator.configure(configName);
        }
        else{
            PropertyConfigurator.configure(getClass().getResource("/ITN_SYNC_SO/environment/logger.properties"));
            logger.warn("Running dir is set to " + System.getProperty("user.dir") + ", could not find file " + System.getProperty("user.dir") + "/logger.conf");
        }

        if(prefix != null)
            changeLoggerFileName(prefix);
    }
    
    private void changeLoggerFileName(String prefix){
        Enumeration appenders = logger.getAllAppenders();
        while(appenders.hasMoreElements()){
            Object appenderObj = appenders.nextElement();
            if(appenderObj instanceof DailyRollingFileAppender){
                DailyRollingFileAppender appender = (DailyRollingFileAppender)appenderObj;
                String filename = getLoggerFileName(prefix, appender.getFile());
                logger.removeAppender(appender);
                try {
                    newAppender = new DailyRollingFileAppender(appender.getLayout(), filename, appender.getDatePattern());
                    logger.addAppender(newAppender);
                } catch (IOException ex) {

                }
            }
            else if(appenderObj instanceof RollingFileAppender){
                RollingFileAppender appender = (RollingFileAppender)appenderObj;
                String filename = getLoggerFileName(prefix, appender.getFile());
                logger.removeAppender(appender);
                try {
                    newAppender = new RollingFileAppender(appender.getLayout(), filename);
                    logger.addAppender(newAppender);
                } catch (IOException ex) {

                }
            }
            else if(appenderObj instanceof FileAppender){
                FileAppender appender = (FileAppender)appenderObj;
                String filename = getLoggerFileName(prefix, appender.getFile());
                logger.removeAppender(appender);
                try {
                    newAppender = new FileAppender(appender.getLayout(), filename);
                    logger.addAppender(newAppender);
                } catch (IOException ex) {

                }
            }
        }
    }

    private String getLoggerFileName(String prefix, String filename){
        File file = new File(filename);
        filename = file.getName();
        filename = prefix + "-" + filename;
        file = new File(file.getParent() + "/" + filename);
        //log.info("Log filename changed to " + file.getPath());
        return file.getPath();
    }
}
