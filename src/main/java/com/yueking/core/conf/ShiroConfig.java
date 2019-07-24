package com.yueking.core.conf;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yueking.core.shiro.realm.UserRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());

//        filterFactoryBean.setLoginUrl("/shiroUtils/noLogin");
//        filterFactoryBean.setUnauthorizedUrl("/shiroUtils/noAuthorize");

        filterFactoryBean.setLoginUrl("intoLogin");
        filterFactoryBean.setUnauthorizedUrl("unauthorized");
//        filterFactoryBean.setSuccessUrl("home");

        Map<String, String> filterChainDefinitionMap = new HashMap<>();
//        filterChainDefinitionMap.put("/shiroUtils/noLogin", "anon");
//        filterChainDefinitionMap.put("/shiroUtils/noAuthorize", "anon");
//        filterChainDefinitionMap.put("/shiroUtils/currentUser", "authc");

        filterChainDefinitionMap.put("/userInfo", "anon");
        filterChainDefinitionMap.put("/findUserByUserName", "authc, roles[admin]");
        filterChainDefinitionMap.put("/intoLogin", "anon");
        filterChainDefinitionMap.put("/subLoginREST", "anon");
        filterChainDefinitionMap.put("/subLogin", "anon");
        filterChainDefinitionMap.put("/unauthorized", "anon");
        filterChainDefinitionMap.put("/logout", "logout");

        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");

        filterChainDefinitionMap.put("/**", "authc");

        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    @Bean
    UserRealm userRealm() {
        return new UserRealm();
    }


    @Bean
    LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * shiro方言  支持shiro标签
     *
     * @return
     */


    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }



//    @Bean
//    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
//        creator.setUsePrefix(true);
//        return creator;
//    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录
     * @return
     */
//    @Bean
//    ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
//        chain.addPathDefinition( "/logout", "logout");
//        chain.addPathDefinition( "/subLogin", "anon");
//        chain.addPathDefinition( "/intoLogin", "anon");
//        chain.addPathDefinition( "/**", "authc");
//        return chain;
//    }





    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @return
     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor AuthorizationAttributeSourceAdvisor(){
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
//        return authorizationAttributeSourceAdvisor;
//    }
}
