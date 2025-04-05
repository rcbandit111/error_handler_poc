package com.logging;

import com.logging.correlation.feign.InternalHeadersFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.logging")
public class TracingHandlersConfiguration {

    @Bean
    public InternalHeadersFilter logger() {
        return new InternalHeadersFilter();
    }
}
