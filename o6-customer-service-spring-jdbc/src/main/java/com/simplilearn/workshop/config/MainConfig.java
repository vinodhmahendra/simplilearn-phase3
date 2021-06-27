package com.simplilearn.workshop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = InfraStructureConfig.class)
//@ComponentScan(basePackages = "com.simplilearn.workshop")
@ComponentScan(basePackages = "com.simplilearn.workshop.repository,com.simplilearn.workshop.services") // Optimized
public class MainConfig {

}
