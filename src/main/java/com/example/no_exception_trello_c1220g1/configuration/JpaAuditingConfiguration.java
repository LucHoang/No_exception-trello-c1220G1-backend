package com.example.no_exception_trello_c1220g1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {

//          if you are using spring security, you can get the currently logged username with following code segment.
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new AuditorAwareImpl();
//        return () -> Optional.ofNullable(username);
    }
}
