package com.barclays.app;

import com.barclays.app.service.JsonVatServiceI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vmusil on 30-Sep-2018.
 */
@Configuration
@ComponentScan(basePackages = {"com.barclays.app.controller"})
public class AppTestConfig {

    @Bean
    public JsonVatServiceI getJsonVatService() {
        return new JsonVatServiceMock();
    }
}
