package com.bcm.app.sendsms;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

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


    }
    
}