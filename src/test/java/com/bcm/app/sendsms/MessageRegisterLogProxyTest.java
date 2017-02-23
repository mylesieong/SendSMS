package com.bcm.app.sendsms;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import java.io.File;

import com.bcm.app.sendsms.*;

public class MessageRegisterLogProxyTest{

    private MessageRegisterLogProxy messageRegisterLogProxy;
        
    @Before
    public void createInstance(){
        // Reset the object e
        messageRegisterLogProxy = new MessageRegisterLogProxy();
        messageRegisterLogProxy.setRealManipulator(new MessageRegister());
    }
    
    @Test
    public void testGetFileEmpty() {
        assertEquals(messageRegisterLogProxy.getFile(), null);
    }
        
    @Test 
    public void testSetNullFile(){
        try{
            messageRegisterLogProxy.setFile(null);
            assertEquals(true, true);  //Exception test: shd not throws exception
        }catch(Exception e ){
            assertEquals(true, false);  //Exception test
        }
    }
    
    @Test
    public void testGetFile() {
        File f = new File("test");
        messageRegisterLogProxy.setFile(f);
        assertEquals(messageRegisterLogProxy.getFile(), f);
    }
        
    @Test 
    public void testFailBeforeManipulate(){
        assertEquals(messageRegisterLogProxy.isSuccess(), false);
    }
    
    @Test 
    public void testFailAfterManipulateWithoutSetFile(){
        messageRegisterLogProxy.manipulate();
        assertEquals(messageRegisterLogProxy.isSuccess(), false);
    }
    
    @Test 
    public void testFailAfterManipulateWithNonExistingFile(){
        File f = new File("test");
        messageRegisterLogProxy.setFile(f);
        messageRegisterLogProxy.manipulate();
        assertEquals(messageRegisterLogProxy.isSuccess(), false);
    }
    
    @Test 
    public void testSuccessAfterManipulateWithExistingFile(){
        try{
            String path = System.getProperty("user.dir");
            File f = new File(path + "\\test.txt");
            if(!f.exists()){
                f.createNewFile();
            }
            messageRegisterLogProxy.setFile(f);        
            messageRegisterLogProxy.manipulate();
            assertEquals(messageRegisterLogProxy.isSuccess(), true);
            f.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @Test 
    public void testIsSuccessResetWhenSetExistingFileThenSetNonExistingFile(){
        try{
            String path = System.getProperty("user.dir");
            File f1 = new File(path + "\\test1.txt");
            if(!f1.exists()){
                f1.createNewFile();
            }
            File f2 = new File(path + "\\test2.txt");
            if(f2.exists()){
                f2.delete();
            }
            messageRegisterLogProxy.setFile(f1);        
            messageRegisterLogProxy.manipulate();
            boolean firstTimeStatus = messageRegisterLogProxy.isSuccess();
            messageRegisterLogProxy.setFile(f2);        
            boolean secondTimeStatus = messageRegisterLogProxy.isSuccess();
            assertEquals(firstTimeStatus && !secondTimeStatus, true);
            f1.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
   
}
