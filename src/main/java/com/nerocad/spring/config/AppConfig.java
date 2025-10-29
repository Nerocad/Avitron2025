package com.nerocad.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.nerocad.spring")
@PropertySource("application.properties")
public class AppConfig {
}
