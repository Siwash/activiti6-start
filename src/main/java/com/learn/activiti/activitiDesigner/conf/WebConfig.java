package com.learn.activiti.activitiDesigner.conf;

import org.activiti.app.security.SecurityUtils;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ranpengfeng
 * @date 2020/8/17
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                Authentication.setAuthenticatedUserId("1");
                return super.preHandle(request, response, handler);
            }
        });
    }
}
