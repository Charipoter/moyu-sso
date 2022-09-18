package com.moyu.sso.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.moyu.sso.mapper")
public class MybatisPlusConfiguration {
}
