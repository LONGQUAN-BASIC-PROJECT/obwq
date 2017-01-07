package com.desksoft.util;

/**
*
*/

import java.util.Collection;
import java.util.List;
import java.util.Map;


public class CollectionUtil {

   /**
    * 判断Collection是否null或者size为0
    * @param c
    */
   public static final boolean isEmpty(Collection<?> c) {
       return null == c || 0 == c.size()? true : false;
   }
   
   
   /**
    * 将List中为null的元素删除掉
    */
   public static final List<?> chopList(List<?> list) {
       if(null == list) {
           return null;
       } else {
           for (int i = 0; i < list.size(); i++) {
               if(null == list.get(i)) {
                   list.remove(i);
                   i--;
               }
           }
           return list;
       }
   }
   
   /**
	 * 判断指定的一个或多个集合, 是否存在空集合
	 * 
	 * @param colls 参数集合, 一个或多个
	 * @return 是否存在空集合
	 */
	public static boolean isEmpty(Collection<?>... colls) {
		boolean result = false;
		
		for (Collection<?> coll : colls) {
			if (null == coll || coll.isEmpty()) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * 判断指定的一个或多个集合, 是否存在空集合
	 * 
	 * @param colls 参数集合, 一个或多个
	 * @return 是否存在空集合
	 */
	public static boolean isEmpty(Map<?, ?>... maps) {
		boolean result = false;
		
		for (Map<?, ?> map : maps) {
			if (null == map || map.isEmpty()) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * 判断指定的一个或多个集合, 是否都是非空集合
	 * 
	 * @param colls 参数集合, 一个或多个
	 * @return 是否都是非空集合
	 */
	public static boolean isNotEmpty(Collection<?>... colls) {
		return !isEmpty(colls);
	}
	
	/**
	 * 判断指定的一个或多个集合, 是否都是非空集合
	 * 
	 * @param colls 参数集合, 一个或多个
	 * @return 是否都是非空集合
	 */
	public static boolean isNotEmpty(Map<?, ?>... maps) {
		return !isEmpty(maps);
	}
	
	/**
    * 判断Collection是否不为空
    * @param c 集合
    * @return 是否不为空
    */
   public static final boolean isNotEmpty(Collection<?> c) {
       return null != c && !c.isEmpty();
   }
   
}
