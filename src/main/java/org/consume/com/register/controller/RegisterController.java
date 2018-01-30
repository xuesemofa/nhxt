package org.consume.com.register.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.consume.com.register.model.RegisterModel;
import org.consume.com.register.service.RegisterService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.user.model.ADD;
import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.util.base64.Base64Util;
import org.consume.com.util.redirect.RedirectUtil;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private StructureService structureService;

    @RequiresAuthentication
    @GetMapping("/toRegister")
    public ModelAndView toRegister() {
        List<StructureModel> companys = structureService.findAll("0");
        List<StructureModel> department = structureService.findAlls("0", "2");
//        List<StructureModel> position = structureService.findAll("3");
        return new ModelAndView("/register")
                .addObject("model", new UserModel())
                .addObject("companys", companys)
                .addObject("department", department);
//                .addObject("position", position);
    }

    @RequiresAuthentication
    @PostMapping("/register")
    public ModelAndView register(@Validated({ADD.class}) @ModelAttribute("model") UserModel model, BindingResult result) {
//数据验证
        if (result.hasErrors()) {
            List<StructureModel> companys = structureService.findAll("0");
            List<StructureModel> department = structureService.findAlls("0", "2");
            return new ModelAndView("/register")
                    .addObject("model", model)
                    .addObject("companys", companys)
                    .addObject("department", department);
        }
//账户是否重复
        UserModel model1 = userService.getByAccount(model.getAccount());
        if (model1 != null) {
            List<StructureModel> companys = structureService.findAll("0");
            List<StructureModel> department = structureService.findAlls("0", "2");
            return new ModelAndView("/register")
                    .addObject("model", new UserModel())
                    .addObject("companys", companys)
                    .addObject("department", department)
                    .addObject("error", "该账户已注册");
        }
//新增账户
        model.setAcctype(2);
        String pwd = Base64Util.encode(model.getPasswrod());
        model.setPasswrod(pwd);
        int i = userService.add(model);
        if (i > 0) {
//            操作数据库成功
            RegisterModel r = new RegisterModel();
            r.setUuid(GetUuid.getUUID());
            r.setAccount(model.getAccount());
            r.setSystimes(System.currentTimeMillis());
            registerService.add(r);
            return new ModelAndView(RedirectUtil.getRddirect() + "/register/registerOK");
        }
//        操作数据库失败
        List<StructureModel> companys = structureService.findAll("0");
        List<StructureModel> department = structureService.findAlls("0", "2");
        return new ModelAndView("/register")
                .addObject("model", new UserModel())
                .addObject("companys", companys)
                .addObject("department", department)
                .addObject("error", "注册失败");
    }

    @RequestMapping("/registerOK")
    public ModelAndView registerOK() {
        return new ModelAndView("/registerOK").addObject("per", "注册成功");
    }
}
