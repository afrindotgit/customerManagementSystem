package com.customermanagementsystem.config;

import com.customermanagementsystem.utils.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.customermanagementsystem.repository")
public class MyBatisConfig {

    @Primary
    @Bean("dataSourceA")
    @ConfigurationProperties(prefix = "spring.datasource-a")
    public DataSource dataSourceA() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dataSourceB")
    @ConfigurationProperties(prefix = "spring.datasource-b")
    public DataSource dataSourceB() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    public MultiRoutingDataSource dynamicDataSource(@Qualifier("dataSourceA") DataSource dataSourceA,
                                                    @Qualifier("dataSourceB") DataSource dataSourceB) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.datasource_a, dataSourceA);
        targetDataSources.put(DataSourceEnum.datasource_b, dataSourceB);

        MultiRoutingDataSource dataSource = new MultiRoutingDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(dataSourceA);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") MultiRoutingDataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(MultiRoutingDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}