package org.consume.com.xq.controller;

import org.consume.com.xq.model.XqModel;
import org.consume.com.xq.service.XqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

@RestController
@RequestMapping("/xq")
public class XqController {
	
	@Value("${page.pageSize}")
	private int pageSizeDefull;
	@Autowired
    private XqService service;
	
	@RequestMapping("/init")
    public ModelAndView init(@RequestParam(required = false) String pageNow,
			@RequestParam(required = false) String pageSize,@RequestParam(value="name",required = false)String name){
    	if (pageNow == null || "".equals(pageNow))
			pageNow = "1";
		if (pageSize == null || "".equals(pageSize))
			pageSize = pageSizeDefull + "";
		if(name!=null&&!"".equals(name)){
			Page<XqModel> pager = service.findByName(Integer.valueOf(pageNow), Integer.valueOf(pageSize),name);
	    	return new ModelAndView("/xq/index").addObject("pager",pager).addObject("name",name);
		}
    	Page<XqModel> pager = service.findAllPage(Integer.valueOf(pageNow), Integer.valueOf(pageSize));
    	return new ModelAndView("/xq/index").addObject("pager",pager);
    }
}
