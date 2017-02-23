package com.bcm.app.sendsms;

import org.apache.log4j.Logger;
import java.io.File;

public class MessageRegisterLogProxy implements FileManipulator{
    
    private FileManipulator mRealManipulator;
    private final Logger mLogger = Logger.getLogger(MessageRegisterLogProxy.class);
    
    private final String LOG_MANIPULATE_BEGINNING = "Discover message file: ";
    private final String LOG_MANIPULATE_DONE_SUCCESS = "Registered file: ";
    private final String LOG_MANIPULATE_DONE_FAILURE = "Failed to register file, file may not exist.";
    
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
        if (this.mRealManipulator.getFile()!= null){
            this.mLogger.info(LOG_MANIPULATE_BEGINNING + this.mRealManipulator.getFile().getName());
        }
        this.mRealManipulator.manipulate();
        if (this.mRealManipulator.isSuccess()){
            this.mLogger.info(LOG_MANIPULATE_DONE_SUCCESS + this.mRealManipulator.getFile().getName());
        }else{
            this.mLogger.info(LOG_MANIPULATE_DONE_FAILURE);
        }
    }
    
    @Override
    public boolean isSuccess(){
        return this.mRealManipulator.isSuccess();
    }
}