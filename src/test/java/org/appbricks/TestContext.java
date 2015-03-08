package org.appbricks;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Sprint context used for unit tests
 */
@Configuration
@ComponentScan("org.appbricks")
@PropertySource("classpath:application.properties")
public class TestContext {
}
