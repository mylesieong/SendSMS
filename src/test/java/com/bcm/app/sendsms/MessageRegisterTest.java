package com.bcm.app.sendsms;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import java.io.File;

public class MessageRegisterTest{

    private FileManipulator messageRegister;
    
    @Before
    public void createInstance(){
        // Reset the object e
        messageRegister = new MessageRegister();
    }
    
    @Test
    public void testGetFileEmpty() {
        assertEquals(messageRegister.getFile(), null);
    }
    
    @Test
    public void testGetFile() {
        File f = new File("test");
        messageRegister.setFile(f);
        assertEquals(messageRegister.getFile(), f);
    }
        
    @Test 
    public void testFailBeforeManipulate(){
        assertEquals(messageRegister.isSuccess(), false);
    }
    
    @Test 
    public void testFailAfterManipulateWithoutSetFile(){
        messageRegister.manipulate();
        assertEquals(messageRegister.isSuccess(), false);
    }
    
    @Test 
    public void testFailAfterManipulateWithNonExistingFile(){
        File f = new File("test");
        messageRegister.setFile(f);
        messageRegister.manipulate();
        assertEquals(messageRegister.isSuccess(), false);
    }
    
    @Test 
    public void testFailAfterManipulateWithExistingFile(){
        try{
            String path = System.getProperty("user.dir");
            File f = new File(path + "\\test.txt");
            if(!f.exists()){
                f.createNewFile();
            }
            messageRegister.setFile(f);        
            messageRegister.manipulate();
            assertEquals(messageRegister.isSuccess(), true);
            f.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
