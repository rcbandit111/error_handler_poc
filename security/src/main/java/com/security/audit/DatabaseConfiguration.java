package com.security.audit;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@Import(SpringSecurityAuditorAware.class)
@EnableJpaAuditing
public class DatabaseConfiguration {

}
