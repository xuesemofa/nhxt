package org.consume.com.ts.controller;

import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.consume.com.ts.model.TsModel;
import org.consume.com.ts.service.TsService;
import org.consume.com.user.model.UserModel;
import org.consume.com.util.redirect.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 投诉信息
 */
@RestController
@RequestMapping("/ts")
public class TsController {

    private static String views = "/ts/";

    @Autowired
    private TsService service;

    //    @RequiresPermissions(value = "ts:init")
    @RequestMapping("/init/{pageNow}")
    public ModelAndView init(@PathVariable("pageNow") int pageNow) {
        Page<TsModel> page = service.findPage(pageNow, 5);
        return new ModelAndView(views + "index")
                .addObject("page", page);
    }

    @GetMapping("/toAdd")
    public ModelAndView toAdd() {
        return new ModelAndView(views + "add")
                .addObject("model", new TsModel());
    }

    @PostMapping("/add")
    public ModelAndView add(@ModelAttribute("model") TsModel model) {
        if (model == null || model.getTs_txt() == null || model.getTs_txt().equals(""))
            return new ModelAndView(views + "add")
                    .addObject("error", "请正确填写投诉内容")
                    .addObject("model", new TsModel());

        Subject subject = SecurityUtils.getSubject();
        UserModel user = (UserModel) subject.getSession().getAttribute("user");
        model.setDates(new Timestamp(new Date().getTime()));
        model.setLx(0);
        model.setUser_id(user.getUuid());
        service.add(model);
        return new ModelAndView(RedirectUtil.getRddirect() + "/ts/init/0");
    }

    @GetMapping("/updateLx")
    public ModelAndView updateLx(@RequestParam("id") String id, @RequestParam("lx") int lx) {
        service.update_lx(id, lx);
        return new ModelAndView(RedirectUtil.getRddirect() + "/ts/init/0");
    }
}
