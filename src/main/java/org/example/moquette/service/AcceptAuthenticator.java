package org.example.moquette.service;

import io.moquette.broker.security.IAuthenticator;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.moquette.config.MqttConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 罗涛
 * @title AcceptAuthenticator
 * @date 2020/10/12 10:43
 * 用户验证拦截类
 */

@Component
public class AcceptAuthenticator implements IAuthenticator {

    @Autowired
    MqttConfig mqttConfig;

    @Override
    public boolean checkValid(String clientId, String username, byte[] password) {
        if (username.equals(mqttConfig.getUsername()) && new String(password).equals(mqttConfig.getPassword()))
            return true;
        else {
            return new String(password).equals(DigestUtils.md5Hex(clientId).toUpperCase());
        }
    }
}
