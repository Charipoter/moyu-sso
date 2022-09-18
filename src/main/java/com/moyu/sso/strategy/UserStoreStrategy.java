package com.moyu.sso.strategy;

import com.moyu.sso.model.SsoUserTO;

public interface UserStoreStrategy {

    SsoUserTO findUser(String username, String password);

}
