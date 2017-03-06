package com.bcm.app.sendsms;

import java.io.File;

/**
 * Class MessageBackuper will copy input file and paste to a backup folder path
 * with the copied file name appending a datetime (YYYYMMDDTTMMSS)
 * 
 * note: MessageBackuper will not change the original file.
 */
public class MessageBackuper implements FileManipulator{
    
    private File mFile;
    private boolean mIsSuccess = false;
    private String mPath;
    
    /* --- getters and setters ---*/
    public void setPath(String path){
        this.mPath = path;
    }
        
    public String getPath(){
        return this.mPath;
    }
    
    /* --- interface override ---*/
    @Override
    public void setFile(File file){
        this.mFile = file;
        this.mIsSuccess = false;
    };

    @Override
    public File getFile(){
        return this.mFile;
    };    
    
    /**
     * Method manipulate in MessageRegister will check the 
     * existence of the file set to the object
     */
    @Override
    public void manipulate(){
        if (this.mFile != null){
            this.mIsSuccess = this.mFile.exists();
        }else{
            this.mIsSuccess = false;
        }
    }

    @Override
    public boolean isSuccess(){
        return mIsSuccess;
    }   
}