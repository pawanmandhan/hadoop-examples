package com.springdeveloper.demo;

import org.apache.pig.ExecType;
import org.apache.pig.impl.PigContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.pig.PigServerFactory;
import org.springframework.data.hadoop.pig.PigServerFactoryBean;
import org.springframework.data.hadoop.pig.PigTemplate;

@ComponentScan
@Configuration
public class AppConfig {

    @Value("${hadoop.host}")
    String hadoopHost;

    @Bean
    org.apache.hadoop.conf.Configuration configuration() {
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", "hdfs://" + hadoopHost + ":8020");
        configuration.set("yarn.resourcemanager.address", hadoopHost + ":8032");
        configuration.set("mapreduce.jobhistory.address", hadoopHost + ":10020");
        configuration.set("mapreduce.framework.name", "yarn");
        System.out.println("fs.defaultFS: " + configuration.get("fs.defaultFS"));
        System.out.println("yarn.resourcemanager.address: " + configuration.get("yarn.resourcemanager.address"));
        System.out.println("mapreduce.jobhistory.address: " + configuration.get("mapreduce.jobhistory.address"));
        System.out.println("mapreduce.framework.name: " + configuration.get("mapreduce.framework.name"));
        return configuration;
    }

    @Bean
    PigTemplate pigTemplate(PigServerFactory pigServerFactory) {
        return new PigTemplate(pigServerFactory);
    }

    @Bean
    public PigServerFactoryBean pigServerFactoryBean(org.apache.hadoop.conf.Configuration configuration) {
        PigContext pigContext = new PigContext(ExecType.MAPREDUCE, configuration);
        PigServerFactoryBean pigFactoryBean = new PigServerFactoryBean();
        pigFactoryBean.setPigContext(pigContext);
        return pigFactoryBean;
    }
}
