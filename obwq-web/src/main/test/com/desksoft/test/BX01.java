package com.desksoft.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.desksoft.entity.xml.XmlBaseData;
import com.desksoft.entity.xml.XmlCategory;
import com.desksoft.entity.xml.XmlWebAddress;
import com.thoughtworks.xstream.XStream;

/**
 * XML与JavaBean相互转换工具类 File: XMLBeanUtils.java User: leizhimin Date: 2008-3-5
 * 14:28:29
 */
public final class BX01 {
	/**
	 * 将Bean转换为XML
	 * 
	 * @param clazzMap
	 *            别名-类名映射Map
	 * @param bean
	 *            要转换为xml的bean对象
	 * @return XML字符串
	 */
	public static String bean2xml(Map<String, Class> clazzMap, Object bean) {
		XStream xstream = new XStream();
		for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
			xstream.alias(m.getKey(), m.getValue());
		}
		String xml = xstream.toXML(bean);
		return xml;
	}

	/**
	 * 将XML转换为Bean
	 * 
	 * @param clazzMap
	 *            别名-类名映射Map
	 * @param xml
	 *            要转换为bean对象的xml字符串
	 * @return Java Bean对象
	 */
	public static Object xml2Bean(Map<String, Class> clazzMap, String xml) {
		XStream xstream = new XStream();
		for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
			xstream.alias(m.getKey(), m.getValue());
		}
		Object bean = xstream.fromXML(xml);
		return bean;
	}

	/**
	 * 获取XStream对象
	 * 
	 * @param clazzMap
	 *            别名-类名映射Map
	 * @return XStream对象
	 */
	public static XStream getXStreamObject(Map<String, Class> clazzMap) {
		XStream xstream = new XStream();
		for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
			xstream.alias(m.getKey(), m.getValue());
		}
		return xstream;
	}

	public static void main_(String[] args) {
		String xml = 
		"<XmlCategory> " +
		"  <categoryid>aaaaaaaa</categoryid>" +
		"  <categoryname>bbbbbbbbbb</categoryname>" +
		"  <webAddress>" +
		"    <XmlWebAddress>" +
		"      <entryId>xxxxxxxxxxxxxxxx</entryId>" +
		"    </XmlWebAddress>" +
		"    <XmlWebAddress>" +
		"      <entryId>yyyyyyyyyyyyyyyyyyyyy</entryId>" +
		"    </XmlWebAddress>" +
		"    <XmlWebAddress>" +
		"      <entryId>yyyyyyyyyyyyyyyyyyyyy</entryId>" +
		"    </XmlWebAddress>" +
		"  </webAddress>" +
		"</XmlCategory>";


		Map<String, Class> cm = new HashMap<String, Class>();
		cm.put("XmlCategory", XmlCategory.class);
		cm.put("XmlWebAddress", XmlWebAddress.class);

		XmlCategory xa = (XmlCategory) new BX01().xml2Bean(cm, xml);

		System.out.println(xa.getCategoryid());
	}

	public static void main_1(String[] args) {
		XmlCategory xa = new XmlCategory();
		xa.setCategoryid("aaaaaaaa");
		xa.setCategoryname("bbbbbbbbbb");

		
		XmlWebAddress xw1 = new XmlWebAddress();
		xw1.setEntryId("xxxxxxxxxxxxxxxx");
		
		XmlWebAddress xw2 = new XmlWebAddress();
		xw2.setEntryId("yyyyyyyyyyyyyyyyyyyyy");
		
		XmlWebAddress xw3 = new XmlWebAddress();
		xw3.setEntryId("yyyyyyyyyyyyyyyyyyyyy");
		
		ArrayList<XmlWebAddress> webAddress = new ArrayList<XmlWebAddress>();
		webAddress.add(xw1);
		webAddress.add(xw2);
		webAddress.add(xw3);
		xa.setWebAddress(webAddress);
		
		
		Map<String, Class> cm = new HashMap<String, Class>();
		cm.put("XmlCategory", XmlCategory.class);
		cm.put("XmlWebAddress", XmlWebAddress.class);
		String str = new BX01().bean2xml(cm, xa);

		System.out.println(str);
	}
	
	public static void main(String[] args) {
		XmlCategory xa = new XmlCategory();
		xa.setCategoryid("aaaaaaaa");
		xa.setCategoryname("bbbbbbbbbb");

		XmlCategory xa1 = new XmlCategory();
		xa1.setCategoryid("aaaaaaaa");
		xa1.setCategoryname("bbbbbbbbbb");
		
		XmlWebAddress xw1 = new XmlWebAddress();
		xw1.setEntryId("xxxxxxxxxxxxxxxx");
		
		XmlWebAddress xw2 = new XmlWebAddress();
		xw2.setEntryId("yyyyyyyyyyyyyyyyyyyyy");
		
		XmlWebAddress xw3 = new XmlWebAddress();
		xw3.setEntryId("yyyyyyyyyyyyyyyyyyyyy");
		
		XmlWebAddress xw4 = new XmlWebAddress();
		xw4.setEntryId("xxxxxxxxxxxxxxxx");
		
		XmlWebAddress xw5 = new XmlWebAddress();
		xw5.setEntryId("yyyyyyyyyyyyyyyyyyyyy");
		
		XmlWebAddress xw6 = new XmlWebAddress();
		xw6.setEntryId("yyyyyyyyyyyyyyyyyyyyy");
		
		ArrayList<XmlWebAddress> webAddress = new ArrayList<XmlWebAddress>();
		webAddress.add(xw1);
		webAddress.add(xw2);
		webAddress.add(xw3);
		
		ArrayList<XmlWebAddress> webAddress1 = new ArrayList<XmlWebAddress>();
		webAddress1.add(xw4);
		webAddress1.add(xw5);
		webAddress1.add(xw6);
		
		xa.setWebAddress(webAddress);
		xa1.setWebAddress(webAddress1);
		
		XmlBaseData xb = new XmlBaseData() ;
		xb.addCategory(xa1);
		xb.addCategory(xa);
		
		Map<String, Class> cm = new HashMap<String, Class>();
		cm.put("XmlCategory", XmlCategory.class);
		cm.put("XmlWebAddress", XmlWebAddress.class);
		cm.put("XmlBaseData", XmlBaseData.class);
		String str = new BX01().bean2xml(cm, xb);

		System.out.println(str);
	}
}