package com.moyu.sso.strategy.support;

import com.moyu.sso.config.SsoProperties;
import com.moyu.sso.model.SsoUserTO;
import com.moyu.sso.strategy.UserStoreStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负责用户持久化数据的操作
 */
@Component
public class UserStoreHelper {
    @Autowired
    private SsoProperties properties;
    @Autowired
    private List<UserStoreStrategy> userStoreStrategies;
    private final Map<String, UserStoreStrategy> userStrategyMap = new HashMap<>();

    @PostConstruct
    private void initUserStoreStrategies() {
        for (UserStoreStrategy userStoreStrategy : userStoreStrategies) {
            userStrategyMap.put(resolveKey(userStoreStrategy), userStoreStrategy);
        }
    }

    private String resolveKey(UserStoreStrategy userStoreStrategy) {
        String suffix = UserStoreStrategy.class.getSimpleName();
        String name = userStoreStrategy.getClass().getSimpleName();
        assert name.endsWith(suffix);
        // 取出前缀转为小写
        return name.substring(0, name.length() - suffix.length()).toLowerCase();
    }

    public SsoUserTO findUser(String username, String password) {
        return userStrategyMap.get(properties.getStore().getType()).findUser(username, password);
    }

}
