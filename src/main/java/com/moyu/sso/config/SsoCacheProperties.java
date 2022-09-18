package com.moyu.sso.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sso.cache")
@Component
@Data
public class SsoCacheProperties {

    private String type;

}
