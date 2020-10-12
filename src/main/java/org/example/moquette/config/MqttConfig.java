package org.example.moquette.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 罗涛
 * @title MqttConfig
 * @date 2020/10/10 17:37
 */
@Component
@ConfigurationProperties("spring.mqtt")
@Setter
@Getter
public class MqttConfig {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 连接地址
     */
    private String hostUrl;
    /**
     * 客户Id
     */
    private String clientId;
    /**
     * 默认连接话题
     */
    private String defaultTopic;
    /**
     * 超时时间
     */
    private Integer timeout;
    /**
     * 保持连接数
     */
    private Integer keepalive;

    private Integer tcpPort;
    private Integer websocketPort;
    private Integer secureWebsocketPort;
    private Boolean allowAnonymous;
    private Integer messageSize;

}


