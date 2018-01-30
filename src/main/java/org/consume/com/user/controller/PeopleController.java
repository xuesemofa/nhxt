package org.consume.com.user.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.consume.com.register.model.RegisterModel;
import org.consume.com.register.service.RegisterService;
import org.consume.com.structure.model.StructureModel;
import org.consume.com.structure.service.StructureService;
import org.consume.com.user.model.ADD;
import org.consume.com.user.model.UPDATE;
import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.util.base64.Base64Util;
import org.consume.com.util.redirect.RedirectUtil;
import org.consume.com.util.resultJson.Htmls;
import org.consume.com.util.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @name 用户管理
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private UserService service;

    @Autowired
    private StructureService structureService;

    @Autowired
    private RegisterService registerService;

    /**
     * init
     *
     * @param pageNow Integer
     * @return ModelAndView
     */
    @RequiresPermissions(value = "yhgl:init")
    @GetMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable(value = "pageNow", required = false) Integer pageNow) {
        Page<UserModel> page = service.findAllPage(pageNow, 5);
        return new ModelAndView("/people/index")
                .addObject("page", page);
    }

    /**
     * 跳转修改密码页面
     *
     * @return ModelAndView
     */
    @RequiresAuthentication
    @GetMapping("/toPassword")
    public ModelAndView toPassword() {
        return new ModelAndView("/people/password");
    }

    /**
     * 修改密码
     *
     * @param password  String
     * @param password1 String
     * @param idNumber  String
     * @return ModelAndView
     */
    @RequiresAuthentication
    @PostMapping("/password")
    public ModelAndView password(@RequestParam("password") String password,
                                 @RequestParam("password1") String password1,
                                 @RequestParam("idNumber") String idNumber) {
        String error = "";
        if (password.equalsIgnoreCase(password1)) {
            Subject subject = SecurityUtils.getSubject();
            UserModel user = (UserModel) subject.getSession().getAttribute("user");
            if (idNumber.equalsIgnoreCase(user.getIdNumber())) {
                boolean isMatch = Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", password);
                if (isMatch) {
                    String pwd = Base64Util.encode(password);
                    service.upPWD(pwd, user.getAccount());
                    return new ModelAndView(RedirectUtil.getRddirect() + "/people/passwordOK");
                } else {
                    error = Htmls.SPAN("red", "密码由字母和数字组成,且长度为8-16（包含）");
                }
            } else {
                error = Htmls.SPAN("red", "验证信息有误，请确认");
                return new ModelAndView("/people/password")
                        .addObject("error", error);
            }
        } else {
            error = Htmls.SPAN("red", "两次输入的密码不一致");
        }
        return new ModelAndView("/people/password")
                .addObject("error", error);
    }

    /**
     * 修改密码成功，重定向
     *
     * @return ModelAndView
     */
    @RequiresAuthentication
    @GetMapping("/passwordOK")
    public ModelAndView passwordOK() {
        return new ModelAndView("/people/passwordOK");
    }

    /**
     * 删除账户
     *
     * @param id String
     * @return ModelAndView 重定向 /people/init/0
     */
    @RequiresPermissions("yhgl:del")
    @GetMapping("/del/{id}")
    public ModelAndView del(@PathVariable("id") String id) {
        service.del(id);
        return new ModelAndView(RedirectUtil.getRddirect() + "/people/init/0");
    }

    /**
     * 重置密码
     *
     * @param id
     * @param account
     * @return
     */
    @RequiresPermissions("yhgl:czmm")
    @GetMapping("/resetting/{id}/{account}")
    public ModelAndView resetting(@PathVariable("id") String id, @PathVariable("account") String account) {
        String pwd = Base64Util.encode("mimachushihua123");
        int i = service.resetting(id, pwd);
        if (i > 0)
            return new ModelAndView(RedirectUtil.getRddirect() + "/people/init/0")
                    .addObject("error", account + "密码初始化成功");
        return new ModelAndView(RedirectUtil.getRddirect() + "/people/init/0")
                .addObject("error", account + "密码初始化失败");
    }

    /**
     * 调岗-此功能延后
     *
     * @param id String
     * @return ModelAndView
     */
//    @RequiresPermissions(value = "people:toPosts")
    @GetMapping("/toPosts/{id}")
    public ModelAndView toPosts(@PathVariable("id") String id) {
//        UserModel model = service.getById(id);
        return null;
    }

    /**
     * 跳转修改页面
     *
     * @param id String
     * @return ModelAndView
     */
    @RequiresPermissions(value = "yhgl:update")
    @GetMapping("/toUpdate/{id}")
    public ModelAndView toUpdate(@PathVariable("id") String id) {
        UserModel model = service.getById(id);
        List<StructureModel> dw = structureService.getByParent(model.getCompany());
        List<StructureModel> bm = structureService.getEqual(model.getDepartment());
        List<StructureModel> zw = structureService.getEqual(model.getPosition());
        return new ModelAndView("/people/update")
                .addObject("model", model)
                .addObject("dw", dw)
                .addObject("bm", bm)
                .addObject("zw", zw);
    }

    /**
     * 修改
     *
     * @param model UserModel
     * @return ModelAnView
     */
    @RequiresPermissions(value = "yhgl:update")
    @PostMapping("/update")
    public ModelAndView update(@Validated({UPDATE.class}) @ModelAttribute("model") UserModel model, BindingResult result) {
        if (result.hasErrors()) {
            List<StructureModel> dw = structureService.getByParent(model.getCompany());
            List<StructureModel> bm = structureService.getEqual(model.getDepartment());
            List<StructureModel> zw = structureService.getEqual(model.getPosition());
            return new ModelAndView("/people/update")
                    .addObject("model", model)
                    .addObject("dw", dw)
                    .addObject("bm", bm)
                    .addObject("zw", zw)
                    .addObject("error",
                            result.getFieldError().getDefaultMessage());
        }
        int i = service.put2(model);
        if (i > 0)
            return new ModelAndView(RedirectUtil.getRddirect() + "/people/init/0")
                    .addObject("error", Htmls.SPAN("green", "资料修改成功"));
        List<StructureModel> dw = structureService.getByParent(model.getCompany());
        List<StructureModel> bm = structureService.getEqual(model.getDepartment());
        List<StructureModel> zw = structureService.getEqual(model.getPosition());
        return new ModelAndView("/people/update")
                .addObject("model", model)
                .addObject("dw", dw)
                .addObject("bm", bm)
                .addObject("zw", zw)
                .addObject("error", Htmls.SPAN("red", "资料修改失败"));
    }

    @RequiresPermissions(value = "yhgl:add")
    @GetMapping("/toAdd")
    public ModelAndView toAdd() {
        List<StructureModel> companys = structureService.findAll("0");
        List<StructureModel> department = structureService.findAlls("0", "2");
        return new ModelAndView("/people/add")
                .addObject("model", new UserModel())
                .addObject("companys", companys)
                .addObject("department", department);
    }

    /**
     * 添加用户
     *
     * @param model  UserModel
     * @param result BindingResult
     * @return ModelAndView
     */
    @RequiresPermissions(value = "yhgl:add")
    @PostMapping("/add")
    public ModelAndView add(@Validated({ADD.class}) @ModelAttribute("model") UserModel model, BindingResult result) {
//数据验证
        if (result.hasErrors()) {
            return new ModelAndView("/people/add")
                    .addObject("model", model);
        }
//账户是否重复
        UserModel model1 = service.getByAccount(model.getAccount());
        if (model1 != null) {
            List<StructureModel> companys = structureService.findAll("0");
            List<StructureModel> department = structureService.findAlls("0", "2");
            return new ModelAndView("/people/add")
                    .addObject("model", new UserModel())
                    .addObject("companys", companys)
                    .addObject("department", department)
                    .addObject("error", "该账户已注册");
        }
//新增账户
        model.setAcctype(2);
        String pwd = Base64Util.encode(model.getPasswrod());
        model.setPasswrod(pwd);
        int i = service.add(model);
        if (i > 0) {
//            操作数据库成功
            RegisterModel r = new RegisterModel();
            r.setUuid(GetUuid.getUUID());
            r.setAccount(model.getAccount());
            r.setSystimes(System.currentTimeMillis());
            registerService.add(r);
            return new ModelAndView(RedirectUtil.getRddirect() + "/people/init/0");
        }
//        操作数据库失败
        List<StructureModel> companys = structureService.findAll("0");
        List<StructureModel> department = structureService.findAlls("0", "2");
        return new ModelAndView("/people/add")
                .addObject("model", new UserModel())
                .addObject("companys", companys)
                .addObject("department", department)
                .addObject("error", "添加失败");
    }
}
