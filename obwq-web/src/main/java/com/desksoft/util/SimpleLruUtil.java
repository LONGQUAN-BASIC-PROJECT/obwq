package com.desksoft.util;

import java.util.*;

public class SimpleLruUtil {

	private static final int MAX_ENTRIES = 500;

	private static Map<String, String> mCache = new LinkedHashMap<String, String>(
			MAX_ENTRIES, .75F, true) {
		private static final long serialVersionUID = -5505005561573808548L;

		protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
			return size() > MAX_ENTRIES;
		}
	};

	public static void putData(String key,String value){
		mCache.put(key, value);
	}
	
	
	public static String getData(String key){
		return mCache.get(key);
	}
	
	public SimpleLruUtil() {
		for (int i = 0; i < 1000; i++) {
			String numberStr = String.valueOf(i);
			mCache.put(numberStr, numberStr);

			System.out.print("\rSize = " + mCache.size() + "\tCurrent value = "
					+ i + "\tLast Value in cache = " + mCache.get(numberStr));
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
			}
		}

		System.out.println("");
	}

	public static void main(String[] args) {
		new SimpleLruUtil();
	}
}