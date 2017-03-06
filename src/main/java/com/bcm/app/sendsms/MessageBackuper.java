package com.bcm.app.sendsms;

import java.io.File;

import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

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
        try{
            if (this.mFile != null && this.mFile.exists()){
                /* get current date and time*/
                DateTime datetime = new DateTime();
                DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddHHmmss");
                // System.out.println(datetime.toString(fmt));
                
                f.createNewFile();
                File f = new File("backup\\testBackupCapability20170306130001.txt");
                if (!f.exists()){    
                }
                this.mIsSuccess = true;
            }else{
                this.mIsSuccess = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSuccess(){
        return mIsSuccess;
    }   
}