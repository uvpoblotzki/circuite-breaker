package io.github.uvpoblotzki.breaker;

import com.netflix.config.DynamicPropertyFactory;
import io.github.uvpoblotzki.breaker.config.HystrixServiceConfiguration;
import io.github.uvpoblotzki.breaker.config.StandardServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring-Konfiguration und starten der Anwendung.
 *
 */
@Configuration
@Import({HystrixServiceConfiguration.class, StandardServiceConfiguration.class})
@EnableAutoConfiguration
public class App {

  static {
    System.setProperty(DynamicPropertyFactory.ENABLE_JMX, "true");
  }

  @Autowired Service service;

  @Bean
  public Controller getController() {
    final Controller controller = new Controller();
    controller.setService(service);
    return controller;
  }

  /**
   * Startet die Anwendung
   * @param args Startparamter
   */
  public static void main(final String[] args) {
    SpringApplication.run(App.class, args);
  }

}
