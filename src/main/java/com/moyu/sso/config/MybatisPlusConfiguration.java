package com.moyu.sso.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@ConditionalOnClass(
        name = {
                "com.mysql.cj.jdbc.Driver",
                "com.baomidou.mybatisplus.core.MybatisConfiguration"
        }
)
@Configuration
@MapperScan("com.moyu.sso.mapper")
public class MybatisPlusConfiguration {
}
