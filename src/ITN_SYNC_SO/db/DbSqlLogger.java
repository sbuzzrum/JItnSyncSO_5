/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ITN_SYNC_SO.db;

import ITN_SYNC_SO.util.*;
import ITN_SYNC_SO.Synch;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class DbSqlLogger {

    private static Logger logger = Logger.getLogger(DbSqlLogger.class);

    public static String filename = "/sql.logger";
    public static String debugFilename = "/debug_sql.logger";

    public static void sqlLog(Synch synch) {
        String appo = "[" + synch.getDateTime().toString() + "]" +
                      " [" + synch.getUseFlg() + "]" +
                      " [" + synch.getIdKit() + "]" +
                      " [" + synch.getCodiceKit() + "]" +
                      " [" + synch.getUseSalaCode() + "]" +
                      " [" + synch.getUseSalaDesc() + "]" + "\n";
        write(filename, appo);
    }

    public static void sqlLog(String sql) {
        String appo = MyUtil.replicate("*", 50) + "\n" + sql + "\n";
        write(filename, appo);
    }

    public static void sqlDebugLog(String sql) {
        String appo = MyUtil.replicate("*", 50) + "\n" + sql + "\n";
        write(debugFilename, appo);
    }

    private static void write(String fileName, String msg) {
        try {
            RandomAccessFile log = new RandomAccessFile(System.getProperty("user.dir") + fileName, "rw");
            log.seek(log.length());
            log.write(msg.getBytes());
            log.close();
        } catch (IOException ex) {
            logger.error("sqlLog: ", ex);
        }
    }
}
