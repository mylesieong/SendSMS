package com.bcm.app.sendsms;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class MessageBackuperTest{
    
    private MessageBackuper messageBackuper;
    
    @Before
    public void createInstance(){
        // Reset the object e
        messageBackuper = new MessageBackuper();
    }
    
    @Test
    public void testGetFileEmpty() {
        assertEquals(messageBackuper.getFile(), null);
    }
   
    @Test 
    public void testSetNullFile(){
        try{
            messageBackuper.setFile(null);
            assertEquals(true, true);  //Exception test: shd not throws exception
        }catch(Exception e ){
            assertEquals(true, false);  //Exception test
        }
    }
    
    @Test
    public void testGetFile() {
        File f = new File("test");
        messageBackuper.setFile(f);
        assertEquals(messageBackuper.getFile(), f);
    }

    @Test 
    public void testSetPath(){
        String path = "C:\\temp";
        messageBackuper.setPath(path);
        assertEquals(path, messageBackuper.getPath());
    }
        
}
