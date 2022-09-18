package com.moyu.sso.strategy;

import com.moyu.sso.model.SsoUser;

import javax.servlet.http.HttpServletRequest;

public interface UserInfoStrategy {

    SsoUser getUserInfo(HttpServletRequest request);

    String storeUserInfo(SsoUser userToStore, HttpServletRequest request);

    void removeUserInfo(HttpServletRequest request);

    String getCacheIdIfPresent(HttpServletRequest request);

}
