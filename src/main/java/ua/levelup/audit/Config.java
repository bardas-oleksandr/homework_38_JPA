package ua.levelup.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
class Config {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareBean();
    }
}
