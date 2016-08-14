package com.desksoft.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.desksoft.entity.ReceiveXmlEntity;
import com.desksoft.entity.xml.XmlCategory;
import com.desksoft.entity.xml.XmlWebAddress;
import com.thoughtworks.xstream.XStream;

/**
	zhengguoxing
 */
public final class XMLUtil {
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

	public static void __main(String[] args) {
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

		XmlCategory xa = (XmlCategory) new XMLUtil().xml2Bean(cm, xml);

		System.out.println(xa.getCategoryid());
	}

	public static void main_(String[] args) {
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
		String str = XMLUtil.bean2xml(cm, xa);

		System.out.println(str);
	}
	
	
	
	/** 
     * 解析微信xml消息 
     * @param strXml 
     * @return 
     */  
    public static ReceiveXmlEntity getWxMsgEntity(String strXml){  
        ReceiveXmlEntity msg = null;  
        try {  
            if (strXml.length() <= 0 || strXml == null)  
                return null;  
               
            // 将字符串转化为XML文档对象  
            Document document = DocumentHelper.parseText(strXml);  
            // 获得文档的根节点  
            Element root = document.getRootElement();  
            // 遍历根节点下所有子节点  
            Iterator<?> iter = root.elementIterator();  
              
            // 遍历所有结点  
            msg = new ReceiveXmlEntity();  
            //利用反射机制，调用set方法  
            //获取该实体的元类型  
            Class<?> c = Class.forName("com.desksoft.entity.ReceiveXmlEntity");  
            msg = (ReceiveXmlEntity)c.newInstance();//创建这个实体的对象  
              
            while(iter.hasNext()){  
                Element ele = (Element)iter.next();  
                //获取set方法中的参数字段（实体类的属性）  
                Field field = c.getDeclaredField(ele.getName());  
                //获取set方法，field.getType())获取它的参数数据类型  
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());  
                //调用set方法  
                method.invoke(msg, ele.getText());  
            }  
        } catch (Exception e) {  
        	//TODO
            System.out.println("xml 格式异常: "+ strXml);  
            e.printStackTrace();  
        }  
        return msg;  
    }  
}