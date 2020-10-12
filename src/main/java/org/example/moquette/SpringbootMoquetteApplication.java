package org.example.moquette;

/**
 * @author 罗涛
 * @title SpringbootEmqApplication
 * @date 2020/10/10 17:09
 */

import lombok.extern.slf4j.Slf4j;
import org.example.moquette.server.MoquetteServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class SpringbootMoquetteApplication {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootMoquetteApplication.class);
        MoquetteServer server = applicationContext.getBean(MoquetteServer.class);
        server.startServer();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.stop();
                log.info("Moquette Server stopped");
            }
        });
    }
}
