package com.dm.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

@EntityScan("com.dm.common.model")
@EnableJpaRepositories({"com.dm.common.repository"})
@SpringBootApplication(scanBasePackages = {
        "com.dm.common",
        "com.dm.consumer"
})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConsumerApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8081"));
        app.run(args);
    }
}