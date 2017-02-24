package com.bcm.app.sendsms;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import java.io.File;

public class MessageFtpUploaderTest{

    private FileManipulator messageFtpUploader;
    
    @Before
    public void createInstance(){
        // Reset the object e
        messageFtpUploader = new MessageFtpUploader();
    }
    
    @Test
    public void testGetFileEmpty() {
        assertEquals(messageFtpUploader.getFile(), null);
    }
   
    @Test 
    public void testSetNullFile(){
        try{
            messageFtpUploader.setFile(null);
            assertEquals(true, true);  //Exception test: shd not throws exception
        }catch(Exception e ){
            assertEquals(true, false);  //Exception test
        }
    }
    
    @Test
    public void testGetFile() {
        File f = new File("test");
        messageFtpUploader.setFile(f);
        assertEquals(messageFtpUploader.getFile(), f);
    }
        
   
}
