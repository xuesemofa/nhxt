package org.consume.com.accountroles.controller;

import com.github.pagehelper.Page;
import org.consume.com.accountroles.modela.AccRolesModel;
import org.consume.com.accountroles.service.AccRolesService;
import org.consume.com.roles.model.RolesModel;
import org.consume.com.roles.service.RoleService;
import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/accRoles")
public class AccRolesController {
    private static String accRoles = "/accRoles";

    @Value("${page.pageSize}")
    private int pageSizeDefull;

    @Autowired
    private UserService userService;

    @Autowired
    private AccRolesService service;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/init")
    public ModelAndView init(@RequestParam(required = false) String pageNow,
                             @RequestParam(required = false) String pageSize,
                             @RequestParam(required = false) String serch) {
        if (pageNow == null || "".equals(pageNow))
            pageNow = "1";
        if (pageSize == null || "".equals(pageSize))
            pageSize = pageSizeDefull + "";
        Page<UserModel> allPage = userService.findAllPage(Integer.parseInt(pageNow), Integer.parseInt(pageSize));
        List<RolesModel> all = roleService.findAll();
        for (UserModel us : allPage) {
            AccRolesModel byAccId = service.findByAccId(us.getUuid());
            if (byAccId == null) {
                us.setDepartment("");
                us.setPosition("");
            } else {
                us.setDepartment(byAccId.getUuid());
                us.setPosition(byAccId.getRoleId());
            }
        }
        return new ModelAndView(accRoles + "/index")
                .addObject("pager", allPage)
                .addObject("model", all);
    }

    @RequestMapping("/addOrUpdate")
    public ModelAndView addOrUpdate(@ModelAttribute("model") AccRolesModel model) {
        AccRolesModel byUuid = service.findByUuid(model.getUuid());
        if (byUuid == null) {
            model.setUuid(GetUuid.getUUID());
            service.add(model);
        } else {
            int update = service.update(model);
        }
        return new ModelAndView("redirect:/accRoles/init");
    }
}
