package com.bcm.app.sendsms;

import org.apache.log4j.Logger;
import java.io.File;

public class MessageRegisterLogProxy implements FileManipulator{
    
    private FileManipulator mRealManipulator;
    private final Logger mLogger = Logger.getLogger(MessageRegisterLogProxy.class);
    
    public void setRealManipulator(FileManipulator fm){
        this.mRealManipulator = fm;
    }
           
    @Override
    public void setFile(File f){
        this.mRealManipulator.setFile(f);
    }   
    
    @Override
    public File getFile(){
        return this.mRealManipulator.getFile();
    }
    
    @Override
    public void manipulate(){
        this.mRealManipulator.manipulate();
    }
    
    @Override
    public boolean isSuccess(){
        return this.mRealManipulator.isSuccess();
    }
}