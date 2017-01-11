package cn.obwq.dto;

import cn.obwq.entity.Agroup;

import java.io.Serializable;

/**
 * 爬取的对象
 * @author guoxing.zgx
 *
 */
public class SechCrawDto extends Agroup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4603847842751475734L;


	private Integer retryCount = 0 ;
	


	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}


	
}
