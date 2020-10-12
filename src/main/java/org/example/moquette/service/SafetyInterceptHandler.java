package org.example.moquette.service;

import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 罗涛
 * @title SafetyInterceptHandler
 * @date 2020/10/12 10:39
 */
@Slf4j
@Component
public class SafetyInterceptHandler extends AbstractInterceptHandler {

    @Override
    public String getID() {
        return SafetyInterceptHandler.class.getName();
    }

    @Override
    public void onConnect(InterceptConnectMessage msg) {
        log.info("connect msg: user = {}, clientId = {}", msg.getUsername(), msg.getClientID());
    }

    @Override
    public void onConnectionLost(InterceptConnectionLostMessage msg) {
        log.info("connect lost msg:{}", msg);
    }


    @Override
    public void onPublish(InterceptPublishMessage msg) {
        log.info("publish msg:{}", msg);
    }


    @Override
    public void onMessageAcknowledged(InterceptAcknowledgedMessage msg) {

    }

    @Override
    public void onSubscribe(InterceptSubscribeMessage msg) {
        super.onSubscribe(msg);
    }

    @Override
    public void onUnsubscribe(InterceptUnsubscribeMessage msg) {
        super.onUnsubscribe(msg);
    }
}
