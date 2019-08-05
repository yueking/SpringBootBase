package com.yueking.core.conf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.yueking.core.ret.RetCode;
import com.yueking.core.ret.RetResult;
import com.yueking.core.ret.ServiceException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/module").setViewName("/admin/modulesManagement");
    }

    /**
     * 静态资源放行
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源放行
//        registry.addResourceHandler("/view/**").addResourceLocations("classpath:/view/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");

        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/LESS/**").addResourceLocations("classpath:/static/LESS/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/pdf/**").addResourceLocations("classpath:/static/pdf/");
        registry.addResourceHandler("/font-awesome/**").addResourceLocations("classpath:/static/font-awesome/");
        registry.addResourceHandler("/email_templates/**").addResourceLocations("classpath:/static/email_templates/");
        registry.addResourceHandler("/locales/**").addResourceLocations("classpath:/static/locales/");

//        registry.hasMappingForPattern("/**");

    }

    /**
     * 修改自定义消息转换器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        converter.setSupportedMediaTypes(getSupportedMediaTypes());
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                // String null -> ""
                SerializerFeature.WriteNullStringAsEmpty,
                //List null -> []
                SerializerFeature.WriteNullListAsEmpty,
                // Number null -> 0
                SerializerFeature.WriteNullNumberAsZero,
                //Boolean null -> false
                SerializerFeature.WriteNullBooleanAsFalse,
                // null
                SerializerFeature.WriteMapNullValue,
                //禁止循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }

    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        return supportedMediaTypes;
    }


    /**
     * 系统异常处理
     *
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(getHandlerExceptionResolver());
    }

    /**
     * 创建异常处理
     *
     * @return
     */
    private HandlerExceptionResolver getHandlerExceptionResolver() {
        HandlerExceptionResolver handlerExceptionResolver = new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                                 Object handler, Exception e) {
                RetResult<Object> result = getResuleByHeandleException(request, handler, e);
                // 业务失败的异常，如“账号或密码错误”
                if (e instanceof ServiceException) {
                    result.setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(RetCode.NOT_FOUND).setMsg("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof UnauthorizedException) {
                    result.setCode(RetCode.UNAUTHEN).setMsg("用户没有访问权限").setData(null);
                } else if (e instanceof UnauthenticatedException) {
                    result.setCode(RetCode.UNAUTHEN).setMsg("用户未登录").setData(null);
                } else if (e instanceof ServletException) {
                    result.setCode(RetCode.FAIL).setMsg(e.getMessage());
                } else {
                    result.setCode(RetCode.INTERNAL_SERVER_ERROR).setMsg("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(), handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod()
                                .getName(), e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                }
                responseResult(response, result);
                return new ModelAndView();
            }
        };
        return handlerExceptionResolver;
    }

    /**
     * 根据异常类型确定返回数据
     *
     * @param request
     * @param handler
     * @param e
     * @return
     */
    private RetResult<Object> getResuleByHeandleException(HttpServletRequest request, Object handler, Exception e) {
        RetResult<Object> result = new RetResult<>();
        if (e instanceof ServiceException) {
            result.setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
            return result;
        }
        if (e instanceof NoHandlerFoundException) {
            result.setCode(RetCode.NOT_FOUND).setMsg("接口 [" + request.getRequestURI() + "] 不存在");
            return result;
        }
        result.setCode(RetCode.INTERNAL_SERVER_ERROR).setMsg("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
        String message;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
                    handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(), e.getMessage());
        } else {
            message = e.getMessage();
        }
        return result;
    }

    /**
     * @param response
     * @param result
     * @Title: responseResult
     * @Description: 响应结果
     * @Reutrn void
     */
    private void responseResult(HttpServletResponse response, RetResult<Object> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
        }
    }


}
