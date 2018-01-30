package org.consume.com.yh.controller;

import org.consume.com.yh.model.YhModel;
import org.consume.com.yh.service.YhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

@RestController
@RequestMapping("/yh")
public class YhController {
	
	@Value("${page.pageSize}")
	private int pageSizeDefull;
	@Autowired
    private YhService service;
	
	@RequestMapping("/init")
    public ModelAndView init(@RequestParam(required = false) String pageNow,
			@RequestParam(required = false) String pageSize,@RequestParam(value="username",required = false)String username){
    	if (pageNow == null || "".equals(pageNow))
			pageNow = "1";
		if (pageSize == null || "".equals(pageSize))
			pageSize = pageSizeDefull + "";
		if(username!=null&&!"".equals(username)){
			Page<YhModel> pager = service.findByUserName(Integer.valueOf(pageNow), Integer.valueOf(pageSize),username);
	    	return new ModelAndView("/yh/index").addObject("pager",pager).addObject("username",username);
		}
    	Page<YhModel> pager = service.findAllPage(Integer.valueOf(pageNow), Integer.valueOf(pageSize));
    	return new ModelAndView("/yh/index").addObject("pager",pager);
    }
}
