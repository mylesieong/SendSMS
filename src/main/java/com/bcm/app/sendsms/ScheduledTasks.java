package com.bcm.app.sendsms;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.sql.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    /* Static properties
     */
    private static final String FTP_ADDRESS = "172.18.255.108";
    private static final int FTP_PORT = 21;
    private static final String FTP_USER = "itoper";
    private static final String FTP_PASSWORD = "IToper01";
    private static final String FTP_FOLDER = "out";
    private static final String SMS_FILE = "C:\\smsfile\\*.msg";
    private static final String SMS_FOLDER = "C:\\smsfile";
    private static final String BKUP_FOLDER = "C:\\smssend";
    private static final String CUR_DIR = System.getProperty("user.dir");
    private static final String INMSG = "inmsg.txt";
    private static final String OUTMSG = "outmsg.txt";
    
    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime10000() {
        log.info("(Interval-10)The time is now {}", dateFormat.format(new Date()));
        try {
            boolean foundMessage = this.retreiveAllSMSFile();
            this.convertMessage();
            if (foundMessage){
                this.ftpSMSFile();
                this.backupFile();
            }
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
    /* Register found message in directory under monitoring
     * @return: Y/N found message or not
     */
    private boolean retreiveAllSMSFile() throws Exception {
        File pendingDirectory = new File(SMS_FOLDER);
        String[] nameOfPendingFiles = pendingDirectory.list();
        
        Connection c = null;
        Statement stmt = null;
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:sms.db");
        c.setAutoCommit(false);
        System.out.println("Opened database for insertion successfully");

        stmt = c.createStatement();
        for (int i = 0; i<nameOfPendingFiles.length; i++){
            System.out.println("Insert File #" + i + " to database...");
            String sql = "INSERT INTO SEND_FILE (sendfile, sendflag) " +
                   "VALUES ('" + nameOfPendingFiles[i] + "', ' ');"; 
            stmt.executeUpdate(sql);
        }
        System.out.println("Finished Insertion");
        stmt.close();
        c.commit();
        c.close();
        
        return true;
        
    }
     
    /* convert the message
     * @return: void
     */
    private void convertMessage(){
         System.out.println("to be implemented: convertMessage()");
    }
    
    /* Upload file according to database and update record flags
     * @return: void
     */
    private void ftpSMSFile() throws Exception{
        Connection c = null;
        Statement stmt = null;
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:sms.db");
        c.setAutoCommit(false);
        System.out.println("Opened database for query successfully");

        stmt = c.createStatement();
        String sql = "SELECT sendfile, sendflag FROM SEND_FILE;"; 
        ResultSet rs = stmt.executeQuery(sql);

        /* Start the ftp service*/
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(FTP_ADDRESS, FTP_PORT);
        int replyCode = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            System.out.println("Operation failed. Server reply code: " + replyCode);
            return ;
        }
        boolean success = ftpClient.login(FTP_USER, FTP_PASSWORD);
        
        if (success){            
            while(rs.next()){
                String fileName = rs.getString("sendfile");
                String sendFlag = rs.getString("sendflag");
                if (sendFlag.equalsIgnoreCase("N")){
                    /* Download the fileA.txt and SWIFT/fileB.txt */
                    String workingDirectory = System.getProperty("user.dir");
                    OutputStream output = new FileOutputStream(workingDirectory + "\\fileB_local.txt");
                    ftpClient.changeWorkingDirectory("SWIFT");
                    ftpClient.retrieveFile("fileB.txt", output);
                    output.close();

                    /* Upload the test.txt at SendSMS folder */
                    InputStream input;
                    input = new FileInputStream(workingDirectory + "\\test.txt");
                    ftpClient.appendFile("test_upload", input);
                    input.close();

                }
            }
        }

        rs.close();
        stmt.close();
        c.commit();
        c.close();
        ftpClient.disconnect();
    }
        
    /* Move the sent files from pending directory to backup directory
     * @return: void
     */
    private void backupFile(){
         System.out.println("In backupFile method");
    }
}