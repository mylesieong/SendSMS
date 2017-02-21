package com.bcm.app.sendsms;

import java.io.File;

public interface FileManipulator{
    public void setFile(File f);
    public void manipulater();
    public boolean isCompleted();
}