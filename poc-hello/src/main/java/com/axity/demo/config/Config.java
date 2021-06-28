package com.axity.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.axity.demo", "com.axity.other" })
public class Config
{
  
  @Bean
  public Object whatever() {
    return new Object();
  }
}
