package com.bcm.app.sendsms;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class MessageConvertorTest{

    private FileManipulator messageConvertor;
    
    public MessageConvertorTest(){
        super();
        messageConvertor =  new MessageConvertor();
    }
    
    @Before 
    public void preparationOfEveryTest(){
        System.out.println("Pre-action before each test.");
    }
    
    @Test
    public void testApp1() {
        assertEquals(1,1);
    }
    
    @Test
    public void testApp2() {
        assertEquals(1,1);
    }
}
