package cn.obwq.result;

import java.util.HashMap;
import java.util.Map;

/**
 * 批量返回结果
 * @author forever
 *
 * @param <K> 返回结果KEY
 * @param <T> 返回对象类型
 */
public class BatchResult<K,T> extends BaseResult {

	private static final long serialVersionUID = -6381676621876289485L;

	private Map<K,T> results = new HashMap<K,T>();
	private Map<K,Map<String/*errorCodes*/,String/*errorDesc*/>> errorCodes = new HashMap<K,Map<String,String>>();

	public Map<K, Map<String, String>> getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(Map<K, Map<String, String>> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public Map<K, T> getResults() {
		return results;
	}

	public void setResults(Map<K, T> results) {
		this.results = results;
	}
	
}
