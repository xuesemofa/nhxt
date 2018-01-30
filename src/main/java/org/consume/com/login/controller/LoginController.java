package org.consume.com.login.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.consume.com.login.model.LoginModel;
import org.consume.com.util.base64.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * login
 */
@RestController
public class LoginController {
    private final static Logger log = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    DefaultKaptcha defaultKaptcha;

    /**
     * @param model  LoginModel
     * @param result BindingResult
     * @return ModelAndView
     */
    @PostMapping("/loginIn")
    public ModelAndView loginIn(
            @Valid @ModelAttribute("model") LoginModel model,
            BindingResult result, HttpServletRequest httpServletRequest) {
        if (result.hasErrors()) {
            return new ModelAndView("/index").addObject("model", model)
                    .addObject("errortext",
                            result.getFieldError().getDefaultMessage());
        }

        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("vrifyCode");

//        验证码判断
//        if (!captchaId.equals(parameter)) {
//            return new ModelAndView("/index").addObject("model", model)
//                    .addObject("errortext", "验证码错误");
//        } else {

        Subject subject = SecurityUtils.getSubject();
        String pwd = Base64Util.encode(model.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(
                model.getUsername(), pwd);
        try {
            subject.login(token);
            return new ModelAndView("redirect:/home/init");
        } catch (UnknownAccountException lae) {
            token.clear();
            return new ModelAndView("/index").addObject("model", model)
                    .addObject("errortext", lae.getMessage());
        } catch (AuthenticationException e) {
            token.clear();
            return new ModelAndView("/index").addObject("model", model)
                    .addObject("errortext", "用户或密码不正确");
        }
//        }

    }

    @GetMapping("/logout")
    public void logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
                if (log.isDebugEnabled()) {
                    log.debug("用户退出登录");
                }
            }
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    // 跳转忘记密码
    @GetMapping("/toForgot")
    public ModelAndView toForgot() {
        return new ModelAndView("/pwd").addObject("xgmmModel", new LoginModel());
    }

    //密码找回
    @PostMapping("/forgot")
    public ModelAndView forgot(@Valid @ModelAttribute("xgmmModel") LoginModel model,
                               BindingResult result) {
        // 表单验证
        if (result.hasErrors()) {
            return new ModelAndView("/pwd").addObject("xgmmModel", model)
                    .addObject("errortextzhmm",
                            result.getFieldError().getDefaultMessage());
        }

        return null;
    }

}
