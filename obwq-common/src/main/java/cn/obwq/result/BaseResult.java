package cn.obwq.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



/**
 * 基础返回结果
 * @author forever
 *
 */
public class BaseResult implements Serializable {

	private static final long serialVersionUID = 3962215109252373857L;
	
	private boolean success = true;		// 操作是否成功
	
	/**
	 * 错误代码
	 */
	private String resultCode;

	/**
	 * 结果说明
	 */
	private String resultDesc;
	
	public Map<String, String> map ;
	
	/**
	 * 当前操作是否成功
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 设置操作结果
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public void addMapEntry(String key,String value){
		if(map == null){
			map = new HashMap<String, String>();
		}
		map.put(key, value);
	}
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}
