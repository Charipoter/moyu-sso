package com.moyu.sso.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "sso")
public class SsoProperties {

    private final Store store = new Store();
    private final Cache cache = new Cache();
    @Data
    public static class Store {

        private String type;

    }
    @Data
    public static class Cache {

        private String type;

    }

}
