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
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime60000() {
        log.info("(Interval-60)The time is now {}", dateFormat.format(new Date()));
        /* Begin the ftp ops*/
        String server = "172.18.255.108";
        int port = 21;
        String user = "itoper";
        String pass = "IToper01";
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return ;
            }
            boolean success = ftpClient.login(user, pass);
            /* Download the fileA.txt and SWIFT/fileB.txt */
            String workingDirectory = System.getProperty("user.dir");
            OutputStream output;
            
            output = new FileOutputStream(workingDirectory + "\\fileA_local.txt");
            ftpClient.retrieveFile("fileA.txt", output);
            output.close();
            
            output = new FileOutputStream(workingDirectory + "\\fileB_local.txt");
            ftpClient.changeWorkingDirectory("SWIFT");
            ftpClient.retrieveFile("fileB.txt", output);
            
            output.close();
            /*/Download the fileA.txt and SWIFT/fileB.txt */
            
            /* Upload the test.txt at SendSMS folder */
            InputStream input;
            input = new FileInputStream(workingDirectory + "\\test.txt");
            ftpClient.appendFile("test_upload", input);
            input.close();
            
            /* /Upload the test.txt at SendSMS folder */
 
            showServerReply(ftpClient);
            if (!success) {
                System.out.println("Could not login to the server");
                return ;
            } else {
                System.out.println("LOGGED IN SERVER");
            }
        } catch (IOException e) {
            System.out.println("Oops! Something wrong happened");
            e.printStackTrace();
        }
    }

    /* Add new task template
    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime2000() {
        log.info("(Interval-2)The time is now {}", dateFormat.format(new Date()));
    }
    */

    private void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}