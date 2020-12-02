package com.example.readwriteseparationv1.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.readwriteseparationv1.dynamic.MultiDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {


    @Bean
    @Qualifier("primaryDataSource")
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean
    @Qualifier("secondDataSource")
    @ConfigurationProperties("spring.datasource.second")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }
    @Bean
    @Qualifier("threeDataSource")
    @ConfigurationProperties("spring.datasource.three")
    public DataSource threeDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public MultiDataSource dynamicDataSource(@Autowired @Qualifier("primaryDataSource") DataSource primaryDataSource,
                                             @Autowired @Qualifier("secondDataSource") DataSource secondDataSource,
                                             @Autowired @Qualifier("threeDataSource") DataSource threeDataSource) {
//        DruidDataSource primaryDataSource = new DruidDataSource();
//        DruidDataSource secondDataSource = new DruidDataSource();
//        primaryDataSource.setUrl("");
//        primaryDataSource.setUsername("");
//        primaryDataSource.setPassword("");
//        primaryDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.PRIMARY.getType(), primaryDataSource);
        targetDataSource.put(DataSourceType.SECOND.getType(), secondDataSource);
        targetDataSource.put(DataSourceType.THREE.getType(), threeDataSource);
        MultiDataSource multiDataSource = new MultiDataSource();
        multiDataSource.setTargetDataSources(targetDataSource);
        multiDataSource.setDefaultTargetDataSource(primaryDataSource);
        return multiDataSource;
    }
}
