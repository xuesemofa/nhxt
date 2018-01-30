package org.consume.com.area.controller;

import java.util.List;

import javax.validation.Valid;

import org.consume.com.area.model.AreaModel;
import org.consume.com.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/area")
public class AreaController {
	private static String views = "/area";
	
	@Autowired
	private AreaService service;
	
	@RequestMapping("/init")
    public ModelAndView init() {
        List<AreaModel> list = service.findAll();
        return new ModelAndView(views + "/index").addObject("list", list);
    }
	
	@RequestMapping("/toAdd")
    public ModelAndView toAdd() {
        return new ModelAndView(views + "/add");
    }
	
	@RequestMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute("areaModel")AreaModel areaModel){
		int i = service.add(areaModel);
		if(i>0){
			return new ModelAndView("redirect:/area/init");
		}
    	return new ModelAndView("/error").addObject("errorStr", "提交申请失败!");
    }
	
	@RequestMapping("/toUpDate")
    public ModelAndView toUpdate(@RequestParam("uuid")String uuid){
		AreaModel areaModel = service.getById(uuid);
		return new ModelAndView(views+"/update").addObject("model",areaModel);
    }
    @RequestMapping("/upDate")
    public ModelAndView update(@Valid @ModelAttribute("areaModel")AreaModel areaModel){
    	int i = service.upDate(areaModel);
    	if(i>0){
    		return new ModelAndView("redirect:"+views+"/init");
    	} 
    	return new ModelAndView("/error").addObject("errorStr", "修改对象失败!");
    }
}
