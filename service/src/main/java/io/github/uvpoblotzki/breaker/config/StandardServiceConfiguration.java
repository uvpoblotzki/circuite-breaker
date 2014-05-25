package io.github.uvpoblotzki.breaker.config;

import io.github.uvpoblotzki.breaker.Service;
import io.github.uvpoblotzki.breaker.ServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("Standard")
public class StandardServiceConfiguration {

  @Bean
  public Service getServiceImpl() {
    return new ServiceImpl();
  }

}
