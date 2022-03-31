package com.zhongzhou.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zhongzhou.common.interceptor.PermissionInterceptor;
import com.zhongzhou.common.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebMvcConfig
 * @Description WebMvcConfig
 * @Date 2020/3/8 18:15
 * @Author wj
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private PermissionInterceptor permissionInterceptor;

    @Value("${constants.file_path}")
    private String filePath;

    /**
     * 添加拦截器
     *
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/api/login");
        excludePathList.add("/api/logout");
        excludePathList.add("/api/register");
        excludePathList.add("/api/upload");
        excludePathList.add("/api/uploadWithURL");
        excludePathList.add("/api/articleUpload");
        excludePathList.add("/api/permission/findMenuListBySysUserId");
        excludePathList.add("/api/permission/findInterfaceListByRoleIdWithChecked/**");
        excludePathList.add("/api/permission/findPermissionTree");
        excludePathList.add("/api/department/findDepTree");
        excludePathList.add("/api/dictionaryType/list");
        excludePathList.add("/api/test/**");
        excludePathList.add("/api/activity/**");
        excludePathList.add("/api/activityUser/**");
        excludePathList.add("/api/wxLogin");
        excludePathList.add("/api/wxJumpActivity");
        excludePathList.add("/websocket/**");
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(excludePathList);
    }

    /**
     * 转换编码为UTF-8
     *
     * @return HttpMessageConverter
     */
    @Bean
    public HttpMessageConverter<String> responseBodyStringConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

    /**
     * 文件上传目录
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+ filePath);
    }
}
