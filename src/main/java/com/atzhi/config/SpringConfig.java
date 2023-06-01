package com.atzhi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan({"com.atzhi.service","com.atzhi.pojo"})
@Import({MysqlConfig.class,MybatisConfig.class})
public class SpringConfig {

}
