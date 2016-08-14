package com.desksoft.entity.xml;

import java.util.ArrayList;

public class XmlBaseData {
	
    private ArrayList<XmlCategory> xmlCategory ;
    
    
    
	public ArrayList<XmlCategory> getXmlCategory() {
		return xmlCategory;
	}

	public void setXmlCategory(ArrayList<XmlCategory> xmlCategory) {
		this.xmlCategory = xmlCategory;
	}

	public void addCategory(XmlCategory xc){
		if(xmlCategory == null){
			xmlCategory = new ArrayList<XmlCategory>();
		}
		this.xmlCategory.add(xc);
	}
	
}