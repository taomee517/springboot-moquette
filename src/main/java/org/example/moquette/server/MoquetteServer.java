package org.example.moquette.server;

import io.moquette.BrokerConstants;
import io.moquette.broker.Server;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.MemoryConfig;
import io.moquette.broker.security.IAuthenticator;
import io.moquette.broker.security.IAuthorizatorPolicy;
import io.moquette.interception.InterceptHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.moquette.config.MqttConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static io.moquette.BrokerConstants.PORT_PROPERTY_NAME;

/**
 * @author 罗涛
 * @title MoquetteServer
 * @date 2020/10/12 10:12
 */

@Slf4j
@Service
public class MoquetteServer {
    @Autowired
    MqttConfig mqttConfig;

    @Autowired
    private IAuthorizatorPolicy authorizatorPolicy;

    @Autowired
    private IAuthenticator authorizator;

    /**
     * Safety相关的拦截器，如果有其它业务，可以再去实现一个拦截器处理其它业务
     */
    @Autowired
    @Qualifier("safetyInterceptHandler")
    private InterceptHandler safetyinterceptHandler;

    private Server mqttServer;

    public void startServer() throws IOException {
//        IResourceLoader configFileResourceLoader = new ClasspathResourceLoader(configFilePath);
//        final IConfig config = new ResourceLoaderConfig(configFileResourceLoader);

        final IConfig config = loadMqttConfig();

        mqttServer = new Server();

        /**添加处理Safety相关的拦截器，如果有其它业务，可以再去实现一个拦截器处理其它业务，然后也添加上即可*/
        List<InterceptHandler> interceptHandlers = Arrays.asList(safetyinterceptHandler);
        /**
         * Authenticator 不显示设置，Server会默认以password_file创建一个ResourceAuthenticator
         * 如果需要更灵活的连接验证方案，可以继承IAuthenticator接口,自定义实现
         */
        mqttServer.startServer(config, interceptHandlers, null,  authorizator, authorizatorPolicy);
        log.info("moquette mqtt server started!");
    }


    public void stop() {
        mqttServer.stopServer();
    }

    private IConfig loadMqttConfig(){
        Properties properties = new Properties();
        properties.put(PORT_PROPERTY_NAME, Integer.toString(mqttConfig.getTcpPort()));
        properties.put(BrokerConstants.ALLOW_ANONYMOUS_PROPERTY_NAME, Boolean.toString(mqttConfig.getAllowAnonymous()));
        properties.put(BrokerConstants.NETTY_MAX_BYTES_PROPERTY_NAME, Integer.toString(mqttConfig.getMessageSize()));
        properties.put(BrokerConstants.WEB_SOCKET_PORT_PROPERTY_NAME, Integer.toString(mqttConfig.getWebsocketPort()));
        properties.put(BrokerConstants.WSS_PORT_PROPERTY_NAME, Integer.toString(mqttConfig.getSecureWebsocketPort()));
        return new MemoryConfig(properties);
    }

}
