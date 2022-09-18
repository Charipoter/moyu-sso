package com.moyu.sso.strategy;

import com.moyu.sso.model.SsoUserTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockUserStoreStrategy implements UserStoreStrategy {

    private List<User> mockUsers = new ArrayList<>();

    public MockUserStoreStrategy() {
        mockUsers.add(new User("1", "cd", "123"));
        mockUsers.add(new User("2", "cc", "111"));
        mockUsers.add(new User("3", "ddd", "333"));
    }
    @Override
    public SsoUserTO findUser(String username, String password) {
        for (User user : mockUsers) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return new SsoUserTO(user.userId, user.getUsername(), "200", "获取成功");
                }
                return new SsoUserTO(user.userId, user.getUsername(), "10000", "密码错误");
            }
        }
        return SsoUserTO.builder().code("10001").msg("账号不存在").build();
    }

    @Data
    @AllArgsConstructor
    private class User {
        private String userId;
        private String username;
        private String password;
    }
}
