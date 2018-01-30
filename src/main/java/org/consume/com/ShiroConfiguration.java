package org.consume.com;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.consume.com.user.service.UserService;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置 Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 * @author Administrator
 */
@Configuration
public class ShiroConfiguration {

    @Autowired(required = false)
    private UserService service;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/Angular/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/email_templates/**", "anon");
        filterChainDefinitionMap.put("/font-awesome/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/style/**", "anon");
        filterChainDefinitionMap.put("/loginIn", "anon");
        filterChainDefinitionMap.put("/defaultKaptcha", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/register/**", "anon");
        filterChainDefinitionMap.put("/**/subordinate", "anon");
        filterChainDefinitionMap.put("/getObject", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");

        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        // 需要拦截的
//        boolean b = service.resetting2("品逸科技", "IT公司");
        // filterChainDefinitionMap.put("/add", "perms[权限添加]");
//        filterChainDefinitionMap.put("/initSubpage", "authc,perms[system:subpage]");
//                filterChainDefinitionMap.put("/gifts/" + g.getUuid(), "authc,perms[" + g.getUuid() + "]");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/index");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/home/init");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        // myShiroRealm.setCredentialsMatcher(HashedCredentialsMatcher());
        return myShiroRealm;
    }

    // /**
    // * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
    // * 所以我们需要修改下doGetAuthenticationInfo中的代码; ）
    // *
    // * @return
    // */
    // @Bean
    // public CredentialsMatcher HashedCredentialsMatcher() {
    // // TODO Auto-generated method stub
    // HashedCredentialsMatcher hashedCredentialsMatcher = new
    // HashedCredentialsMatcher();
    //
    // hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
    // hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于
    // // md5(md5(""));
    //
    // return hashedCredentialsMatcher;
    // }

    // /**
    // * 配置shiro redisManager 使用的是shiro-redis开源插件
    // *
    // * @return
    // */
    // public RedisManager redisManager() {
    // RedisManager redisManager = new RedisManager();
    // redisManager.setHost(host);
    // redisManager.setPort(port);
    // redisManager.setExpire(1800);// 配置缓存过期时间
    // redisManager.setTimeout(timeout);
    // // redisManager.setPassword(password);
    // return redisManager;
    // }
}
