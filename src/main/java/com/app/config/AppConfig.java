package com.app.config;

import com.app.Main;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vmusil on 27-Sep-2018.
 */
@Configuration
@ComponentScan(basePackages = {"com.app.service", "com.app.controller"})
public class AppConfig {

    @Bean
    public Main app() {
        return new Main();
    }
}
