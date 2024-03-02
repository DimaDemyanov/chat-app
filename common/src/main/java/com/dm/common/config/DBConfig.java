package com.dm.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.dm.common.model")
@EnableJpaRepositories({"com.dm.common.repository"})
@Configuration
public class DBConfig {
}
