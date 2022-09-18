package com.moyu.sso.strategy.support;

import com.moyu.sso.config.SsoCacheProperties;
import com.moyu.sso.model.SsoUser;
import com.moyu.sso.strategy.UserInfoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负责用户缓存数据的操作
 */
@Component
public class UserInfoHelper {

    @Autowired
    private SsoCacheProperties properties;

    private final Map<String, UserInfoStrategy> userInfoStrategyMap = new HashMap<>();
    @Autowired
    private List<UserInfoStrategy> userInfoStrategies;

    @PostConstruct
    private void initUserStoreStrategies() {
        for (UserInfoStrategy userInfoStrategy : userInfoStrategies) {
            userInfoStrategyMap.put(resolveKey(userInfoStrategy), userInfoStrategy);
        }
    }

    private String resolveKey(UserInfoStrategy userInfoStrategy) {
        String suffix = UserInfoStrategy.class.getSimpleName();
        String name = userInfoStrategy.getClass().getSimpleName();
        assert name.endsWith(suffix);
        // 取出前缀转为小写
        return name.substring(0, name.length() - suffix.length()).toLowerCase();
    }

    public SsoUser getUserInfo(HttpServletRequest request) {
        return userInfoStrategyMap.get(properties.getType()).getUserInfo(request);
    }

    public String storeUserInfo(SsoUser userToStore, HttpServletRequest request) {
        return userInfoStrategyMap.get(properties.getType()).storeUserInfo(userToStore, request);
    }

    public void removeUserInfo(HttpServletRequest request) {
        userInfoStrategyMap.get(properties.getType()).removeUserInfo(request);
    }

    public String getCacheIdIfPresent(HttpServletRequest request) {
        return userInfoStrategyMap.get(properties.getType()).getCacheIdIfPresent(request);
    }

}
