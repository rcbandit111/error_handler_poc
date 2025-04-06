package com.security.audit;

import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import com.logging.correlation.feign.InternalHeadersContext;

@Component
@ConditionalOnClass({InternalHeadersContext.class})
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public SpringSecurityAuditorAware() {
    }

    public Optional<String> getCurrentAuditor() {
        String userName = (String)InternalHeadersContext.getInternalHeaders().getOrDefault("X-INTERNAL-USER-ID", "test");
        return Optional.of(userName);
    }
}
