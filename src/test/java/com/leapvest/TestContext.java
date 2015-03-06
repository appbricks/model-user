package com.leapvest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Sprint context used for unit tests
 */
@Configuration
@ComponentScan("com.leapvest")
@PropertySource("classpath:application.properties")
public class TestContext {
}
