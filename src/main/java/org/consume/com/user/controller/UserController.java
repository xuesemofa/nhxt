package org.consume.com.user.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.util.base64.Base64Util;
import org.consume.com.util.date.Dates2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @name 本类管理员方法请勿动
 * @shiro user:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static String user = "/user";

    @Value("${page.pageSize}")
    private int pageSizeDefull;

    @Autowired
    private UserService service;

    /**
     * @param pageNow
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") Integer pageNow) {
        Page<UserModel> allPage = service.findAllPage(pageNow, pageSizeDefull);
        return new ModelAndView(user + "/index").addObject("pager", allPage);
    }

    /**
     * 管理员方法
     *
     * @param id
     * @return
     */
    @RequiresRoles("admins")
    @PostMapping("/getByCD")
    public List<UserModel> getByCD(@RequestParam("id") String id) {
        return service.getByCD(id);
    }

    @RequiresAuthentication
    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") String id) {
        UserModel model = service.getById2(id);
        return new ModelAndView("/user/index")
                .addObject("model", model);
    }

    /**
     * 管理员方法
     *
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("/admins")
    public String get() {
        UserModel model = new UserModel();
        model.setUsername("管理员");
        model.setAcctype(1);
        String password = Dates2.getDateString1(new Date());
        model.setAccount("guanliyuan" + password);
        String pwd = Base64Util.encode("guanliyuan" + password);
        model.setPasswrod(pwd);
        UserModel user = service.getByAccount(model.getAccount());
        if (user != null)
            return "自动生成的管理员已经存在";
        service.add(model);
        return "管理员添加成功";
    }
}
