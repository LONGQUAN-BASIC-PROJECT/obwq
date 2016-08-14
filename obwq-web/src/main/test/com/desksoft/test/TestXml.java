package com.desksoft.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.desksoft.entity.xml.XmlCategory;
import com.desksoft.entity.xml.XmlWebAddress;
import com.wutka.jox.JOXBeanInputStream;
import com.wutka.jox.JOXBeanOutputStream;

public class TestXml {

	public static void main(String[] args) {
		/*
		JOXBeanInputStream joxIn = null;
		try {
			joxIn = new JOXBeanInputStream(new FileInputStream("c:/aa.xml"));
			XmlCategory xa = (XmlCategory) joxIn.readObject(XmlCategory.class);
			
			System.out.println(xa);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	*/
//		List<XmlCategory> calist = new ArrayList<XmlCategory>();
		XmlCategory xa = new XmlCategory();
		xa.setCategoryid("aaaaaaaa");
		xa.setCategoryname("bbbbbbbbbb");
		
		ArrayList<XmlWebAddress> ab = new ArrayList<XmlWebAddress>();
		XmlWebAddress xw = new XmlWebAddress();
		xw.setEntryId("rere");
		xw.setEntryName("xasdfasdf");
		ab.add(xw);
		XmlWebAddress xw1 = new XmlWebAddress();
		xw1.setEntryId("rere");
		xw1.setEntryName("xasdfasdf");
		ab.add(xw1);
		
		xa.setWebAddress(ab);
		
		
		String str = toXML(xa);
		System.out.println(str);
	}
	
	
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
}
