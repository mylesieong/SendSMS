package com.bcm.app.sendsms;

import java.io.File;

public class MessageUploader implements FileManipulator{
    @Override
    public void setFile(File file){
        System.out.println("Method body");
    };

    @Override
    public void manipulater(){
        System.out.println("Method body");
    }

    @Override
    public boolean isCompleted(){
        System.out.println("Method body");
        return true;
    }   
}