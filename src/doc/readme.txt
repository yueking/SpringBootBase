========================================================================
1.pom.xml     配置依赖支持 并且项目输出为war 同时支持tomcat发布及程序自运行
========================================================================
    1.1
    1.2 Application 需要重载 SpringBootServletInitializer.configure 方法
        为了支持 war 包发布
========================================================================

========================================================================
2.application.properties 配置程序
    2.1
        springboot2.0配置连接池（hikari、druid）
========================================================================


========================================================================
3.系统配置 com.yueking.core.conf 配置spring boot database mvc 等
========================================================================

------------------------------------------------------------------------
dictDao.getOne(key); 存在 lazy 问题：
解决：spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
------------------------------------------------------------------------
打开ehcache 缓存后
问题再次出现
解决:
getOne(key) 换成 findById(key)
原因:
    getOne 是 lazy findById 不是lazy
========================================================================
spring boot 静态资源问题
========================================================================
如果修改了 WebConfig WebMvcConfigurationSupport 默认配置
    1.需要静态资源放行配置
        @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //静态资源放行
                registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
                registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
            }
    2.如果集成 shiro 等安全框架 需要对静态资源目录 设置为透明访问资源
          filterChainDefinitionMap.put("/images/**", "anon");
          filterChainDefinitionMap.put("/css/**", "anon");
          filterChainDefinitionMap.put("/js/**", "anon");
          filterChainDefinitionMap.put("/**", "authc");
    3.shiro 过滤器映射 一定要用LinkedHashMap 这是个巨坑！ 因为映射需有顺序
     Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

