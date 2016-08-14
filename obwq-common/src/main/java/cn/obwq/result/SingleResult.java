package cn.obwq.result;


/**
 * 单个结果返回
 *
 * @param <T>
 */
public class SingleResult<T> extends BaseResult {

	private static final long serialVersionUID = -943558346236174497L;
	
	private T result;
	
	private Object extParamObject ;
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	public void setResultCodeAndDesc(ResultDesc desc){
		setResultCode(desc.getCode());
		setResultDesc(desc.getDesc());
	}

	public Object getExtParamObject() {
		return extParamObject;
	}

	public void setExtParamObject(Object extParamObject) {
		this.extParamObject = extParamObject;
	}
}
