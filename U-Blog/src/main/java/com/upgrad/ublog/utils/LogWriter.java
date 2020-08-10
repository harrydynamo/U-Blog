package com.upgrad.ublog.utils;

import java.io.*;

/**
 * TODO: 8.1. Implement writeLog() method with the following method signature.
 *  public static void writeLog(String logMessage, String path) throws IOException
 *  This method should append the log message to the log file that is stored at the
 *  Input path.
 */

public class LogWriter {
    public static void writeLog(String logMessage, String path) throws IOException {
        String currDirectory = System.getProperty("user.dir");
        System.out.println(currDirectory);

        try(BufferedWriter br = new BufferedWriter(new FileWriter(path, true))) {
            br.write(logMessage);
        } catch (FileNotFoundException fne) {}
    }
}
