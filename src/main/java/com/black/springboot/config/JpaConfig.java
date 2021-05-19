package com.black.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
//JPA Auditing 활성화
@EnableJpaAuditing
public class JpaConfig {
}
