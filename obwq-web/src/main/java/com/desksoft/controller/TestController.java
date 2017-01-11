package com.desksoft.controller;

import cn.obwq.result.SingleResult;
import com.desksoft.common.constants.AgroupEnum;
import cn.obwq.dto.SechCrawDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @author forever
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	private static Logger loger = LoggerFactory.getLogger(TestController.class);
	

	@ResponseBody
	@RequestMapping(value="/index/list")
	public Object index(HttpServletRequest request,HttpServletResponse response){

		SingleResult<List<SechCrawDto>> result = new SingleResult<List<SechCrawDto>>();
		try {

			List<SechCrawDto> list = new ArrayList<SechCrawDto>();

			SechCrawDto sec = new SechCrawDto();
			sec.setName(AgroupEnum.ZHIHU.name());
			sec.setUrl("http://daily.zhihu.com/");
			sec.setId(1l);


			SechCrawDto sec2 = new SechCrawDto();
			sec2.setName(AgroupEnum.ZHIHU.name());
			sec2.setUrl("http://daily.zhihu.com/");
			sec2.setId(1l);

			list.add(sec);
			list.add(sec2);

			result.setResult(list);
		}catch (Exception e) {
			result.setSuccess(Boolean.FALSE);
		}

		return result;
	}


	@ResponseBody
	@RequestMapping(value="/index/object")
	public Object index_object(HttpServletRequest request,HttpServletResponse response){

		SingleResult<SechCrawDto> result = new SingleResult<SechCrawDto>();
		try {
			SechCrawDto sec = new SechCrawDto();
			sec.setName(AgroupEnum.ZHIHU.name());
			sec.setUrl("http://daily.zhihu.com/");
			sec.setId(1l);
			result.setResult(sec);
		}catch (Exception e) {
			result.setSuccess(Boolean.FALSE);
		}

		return result;
	}



}
