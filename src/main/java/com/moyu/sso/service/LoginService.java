package com.moyu.sso.service;

import com.moyu.sso.model.SsoUser;
import com.moyu.sso.model.SsoUserTO;
import com.moyu.sso.strategy.support.UserInfoHelper;
import com.moyu.sso.strategy.support.UserStoreHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginService {

    @Autowired
    private UserStoreHelper userStoreHelper;
    @Autowired
    private UserInfoHelper userInfoHelper;

    public SsoUserTO findUser(String username, String password) {
        return userStoreHelper.findUser(username, password);
    }

    public SsoUser getUserInfo(HttpServletRequest request) {
        return userInfoHelper.getUserInfo(request);
    }

    public String storeUserInfo(SsoUser userToStore, HttpServletRequest request) {
        return userInfoHelper.storeUserInfo(userToStore, request);
    }

    public void removeUserInfo(HttpServletRequest request) {
        userInfoHelper.removeUserInfo(request);
    }

    public String getCacheIdIfPresent(HttpServletRequest request) {
        return userInfoHelper.getCacheIdIfPresent(request);
    }

}
