package com.dm.api.config;

import com.dm.common.config.DBConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({DBConfig.class})
@Configuration
public class ApiConfig { }
