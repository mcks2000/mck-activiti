package com.mck.activiti.common.config;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Description: activiti工作流引擎初始化配置
 * @Author: mck
 * @Date: 2022/5/24 10:17
 **/
@Configuration
public class ActivitiConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private ActivitiIdGenerate activitiIdGenerate;

    // 对引擎配置信息进行初始化
    @Bean
    public ProcessEngineConfigurationImpl getProcessEngineConfiguration(ProcessEngineConfigurationImpl processEngineConfiguration) {
        //id生成策略
        processEngineConfiguration.setIdGenerator(activitiIdGenerate);
        //设置DbSqlSessionFactory的uuidGenerator，否则流程id，任务id，实例id依然是用DbIdGenerator生成
        processEngineConfiguration.getDbSqlSessionFactory().setIdGenerator(activitiIdGenerate);
        //设置流程图片中文乱码
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        // 启用数据库日志
//        processEngineConfiguration.setEnableDatabaseEventLogging(true);
        return processEngineConfiguration;
    }

    // 记录流程日志
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager manager) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(manager);
        configuration.setAsyncExecutorActivate(true);
        configuration.setHistoryLevel(HistoryLevel.FULL);
        configuration.setDatabaseSchema("activity");
        configuration.setDbHistoryUsed(true);
        configuration.setDatabaseSchemaUpdate("true");
        //是否开启dblog
        configuration.setEnableDatabaseEventLogging(true);
        return configuration;
    }
}
