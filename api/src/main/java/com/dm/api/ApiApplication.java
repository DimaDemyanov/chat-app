package com.dm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.dm.common.model")
@EnableJpaRepositories({"com.dm.common.repository"})
@SpringBootApplication(scanBasePackages = {
        "com.dm.common",
        "com.dm.api"
})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}