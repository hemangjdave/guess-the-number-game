package com.techRevolution.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = "com.techRevolution")
@Import(GameConfig.class)
@Configuration
public class AppConfig {

}
