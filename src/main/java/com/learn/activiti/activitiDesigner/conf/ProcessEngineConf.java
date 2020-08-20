package com.learn.activiti.activitiDesigner.conf;

import lombok.Data;
import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author ranpengfeng
 * @date 2020/8/10
 */
@Configuration
@Data
public class ProcessEngineConf {
    private Logger logger = LoggerFactory.getLogger(ProcessEngineConf.class);
    // 从 yml 配置文件中获取 mysql 配置信息
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    /**
     * 初始化流程引擎
     * @return
     */
    @Primary
    @Bean
    public ProcessEngine initProcessEngine() {
        logger.info("=============================ProcessEngineBegin=============================");

        // 流程引擎配置
        ProcessEngineConfiguration cfg = null;

        try {
            cfg = new StandaloneProcessEngineConfiguration()
                    .setJdbcUrl(url)
                    .setJdbcUsername(username)
                    // 对密码进行解密
                    .setJdbcPassword(password)
                    .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                    // 初始化基础表，不需要的可以改为 DB_SCHEMA_UPDATE_FALSE
                    .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 初始化流程引擎对象
        ProcessEngine processEngine = cfg.buildProcessEngine();
        logger.info("=============================ProcessEngineEnd=============================");
        return processEngine;
    }

    //八大接口
    // 业务流程的定义相关服务
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }

    // 流程对象实例相关服务
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    // 流程任务节点相关服务
    @Bean
    public TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    // 流程历史信息相关服务
    @Bean
    public HistoryService historyService(ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }

    // 表单引擎相关服务
    @Bean
    public FormService formService(ProcessEngine processEngine){
        return processEngine.getFormService();
    }

    // 用户以及组管理相关服务
    @Bean
    public IdentityService identityService(ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }

    // 管理和维护相关服务
    @Bean
    public ManagementService managementService(ProcessEngine processEngine){
        return processEngine.getManagementService();
    }

    // 动态流程服务
    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine){
        return processEngine.getDynamicBpmnService();
    }
}
