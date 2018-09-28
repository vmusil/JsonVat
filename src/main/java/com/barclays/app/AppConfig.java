package com.barclays.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vmusil on 27-Sep-2018.
 */
@Configuration
@ComponentScan(basePackages = {"com.barclays.app.service", "com.barclays.app.controller"})
public class AppConfig {

    @Bean
    public App app() {
        return new App();
    }
}
