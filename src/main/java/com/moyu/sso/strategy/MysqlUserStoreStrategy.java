package com.moyu.sso.strategy;

import com.moyu.sso.model.SsoUserTO;
import org.springframework.stereotype.Component;

@Component
public class MysqlUserStoreStrategy implements UserStoreStrategy {

    @Override
    public SsoUserTO findUser(String username, String password) {

        return SsoUserTO.builder().code("10001").msg("账号不存在").build();
    }

}
