package com.learn.activiti.activitiDesigner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"org.activiti.app","com.learn.activiti.activitiDesigner"},lazyInit = true)
@EnableJpaRepositories({"org.activiti.app.repository"})
@EntityScan("org.activiti.app.domain")
public class ActivitiDesignerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiDesignerApplication.class, args);
    }

}
