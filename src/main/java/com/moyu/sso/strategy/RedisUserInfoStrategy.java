package com.moyu.sso.strategy;

import com.moyu.sso.consts.Const;
import com.moyu.sso.model.SsoUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class RedisUserInfoStrategy implements UserInfoStrategy {
    @Override
    public SsoUser getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SsoUser user = null;
        // session 存在，将数据反序列化取出
        if (session != null) {
            user = (SsoUser) session.getAttribute(Const.USER_INFO_CACHE_KEY);
        }
        return user;
    }

    @Override
    public String storeUserInfo(SsoUser userToStore, HttpServletRequest request) {
        // 创建新的 session
        HttpSession session = request.getSession();
        session.setAttribute(Const.USER_INFO_CACHE_KEY, userToStore);

        return session.getId();
    }

    @Override
    public void removeUserInfo(HttpServletRequest request) {
        // 把 session 删除
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 注意：redis 内部还会留存5分钟，客户端令牌没了
            session.invalidate();
        }
    }

    @Override
    public String getCacheIdIfPresent(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? session.getId() : null;
    }

}
