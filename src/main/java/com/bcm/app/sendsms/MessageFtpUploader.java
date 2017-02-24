package com.bcm.app.sendsms;

import java.io.File;

public class MessageFtpUploader implements FileManipulator{
    
    private File mFile;
    private boolean mIsSuccess = false;
    private String mFtpAddress;
    private int mFtpPort;
    private String mFtpUser;
    private String mFtpPassword;
    private String mFtpFolder;
    
    /* --- Setters ---*/
    public void setFtpAddress(String address){
        this.mFtpAddress = address;
    }
    
    public void setFtpPort(int port){
        this.mFtpPort = port;
    }
    
    public void setFtpUser(String user){
        this.mFtpUser = user;
    }
    
    public void setFtpPassword(String password){
        this.mFtpPassword = password;
    }
    
    public void setFtpFolder(String folder){
        this.mFtpFolder = folder;
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
     * Method manipulate in MessageUploader will check the 
     * existence of the file set to the object
     */
    @Override
    public void manipulate(){

    }

    @Override
    public boolean isSuccess(){
        return mIsSuccess;
    }   
}