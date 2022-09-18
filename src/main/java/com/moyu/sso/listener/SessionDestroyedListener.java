package com.moyu.sso.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SessionDestroyedListener implements ApplicationListener<SessionDestroyedEvent> {
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        log.debug(event.getSession().getId() + "已经被删除了，保留5分钟");
    }
}
