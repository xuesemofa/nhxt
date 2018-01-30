package org.consume.com.userJurisdiction.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.jurisdiction.service.JurisdictionService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.userJurisdiction.model.UserJurisdictionModel;
import org.consume.com.userJurisdiction.service.UserJurisdictionService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/userJurisdiction")
public class UserJurisdictionController {

    private static String views = "/userJurisdiction";

    @Value("${page.pageSize}")
    private int pageSizeDefull;
    @Autowired
    private UserJurisdictionService service;
    @Autowired
    private JurisdictionService jurisdictionService;
    @Autowired
    private UserService userService;
    @Autowired
    private StructureService structureService;

    @RequiresRoles("admins")
    @RequestMapping("/init")
    public ModelAndView init(@RequestParam(required = false,value = "pageNow") String pageNow,
                             @RequestParam(required = false,value = "pageSize") String pageSize)
    {
        if (pageNow == null || "".equals(pageNow)){
            pageNow = "1";
        }
        if (pageSize == null || "".equals(pageSize))
        {
            pageSize = pageSizeDefull + "";
        }
        List<StructureModel> byParents = structureService.getByParents();
        List<JurisdictionModel> all = jurisdictionService.findAll();
        List<UserModel> usermodel = userService.findAll();
        return new ModelAndView(views+"/index")
                .addObject("usermodel",usermodel)
                .addObject("pager",all)
                .addObject("list",byParents);
    }

    @RequiresRoles("admins")
    @RequestMapping("/addOrUpdate")
    public ModelAndView addOrUpdate(@RequestParam(value = "jurisdictionId",required = false)List<String> jurisdictionId,
                                    @RequestParam("userId")String userId)
    {
        List<UserJurisdictionModel> byUserId = service.findByUserId(userId);
            UserJurisdictionModel model = new UserJurisdictionModel();
        if(byUserId != null && byUserId.size() > 0)
        {
            service.delByUserId(userId);
        }
        if(jurisdictionId !=null && jurisdictionId.size() > 0)
        {
            for (String s: jurisdictionId)
            {
                model.setUuid(GetUuid.getUUID());
                model.setJurisdictionId(s);
                model.setUserId(userId);
                int add = service.add(model);
            }
        }
        return new ModelAndView("redirect:/userJurisdiction/init");
    }

    @RequiresRoles("admins")
    @RequestMapping("/findByUserId")
    public List<UserJurisdictionModel> findByUserId(@RequestParam("id")String id)
    {
        List<UserJurisdictionModel> byUserId = service.findByUserId(id);
        return byUserId;
    }
}
