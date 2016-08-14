package com.desksoft.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.greatwall.csi.np.model.ApproxInfo;
import com.greatwall.csi.np.model.ApproxItem;
import com.wutka.jox.JOXBeanInputStream;
import com.wutka.jox.JOXBeanOutputStream;

public   class  JOXUtils {

    /** 
    * Retrieves a bean object for the
    * received XML and matching bean class
     */ 
    public   static  Object fromXML(String xml, Class className) {
       ByteArrayInputStream xmlData  =   new  ByteArrayInputStream(xml.getBytes());
       JOXBeanInputStream joxIn  =   new  JOXBeanInputStream(xmlData);
        try  {
            return  (Object) joxIn.readObject(className);
       }  catch  (IOException exc) {
           exc.printStackTrace();
            return   null ;
       }
        finally  {
            try  {
               xmlData.close();
               joxIn.close();
           }  catch  (Exception e) {
               e.printStackTrace();
           }
       }
   }

    /** 
    * Returns an XML document String for the received bean
     */ 
    public   static  String toXML(Object bean) {
       ByteArrayOutputStream xmlData  =   new  ByteArrayOutputStream();
       JOXBeanOutputStream joxOut  =   new  JOXBeanOutputStream(xmlData);
        try  {
           joxOut.writeObject(beanName(bean), bean);
            return  xmlData.toString();
       }  catch  (IOException exc) {
           exc.printStackTrace();
            return   null ;
       }
        finally  {
            try  {
               xmlData.close();
               joxOut.close();
           }  catch  (Exception e) {
               e.printStackTrace();
           }
       }
   }

    /** 
    * Find out the bean class name
     */ 
    private   static  String beanName(Object bean) {
       String fullClassName  =  bean.getClass().getName();
       String classNameTemp  =  fullClassName.substring(
           fullClassName.lastIndexOf( " . " )  +   1 ,
           fullClassName.length()
           );
        return  classNameTemp.substring( 0 ,  1 )
            +  classNameTemp.substring( 1 );
   }
   
    public   static   void  main(String[] args) {
       ApproxItem approxItem  =   new  ApproxItem();
       approxItem.setMonth( 3923 );
       approxItem.setExpose( 0.23 );
       approxItem.setMap( new  HashMap());
       ApproxInfo approxInfo  =   new  ApproxInfo();
       approxInfo.setBirth( 111 );
       approxInfo.setIdNo( " aaa " );
       approxItem.getMap().put( " ss " , approxInfo);
       approxInfo.setBirth( 222 );
       approxInfo.setIdNo( " bbb " );
       approxItem.getMap().put( " dd " , approxInfo);
       approxItem.setList( new  ArrayList( 1 ));
       approxItem.getList().add(approxInfo);
       System.out.println( " JOXUtils.toXML(approxItem)= " );
      System.out.println(JOXUtils.toXML(approxItem));
   }
}