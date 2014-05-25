package io.github.uvpoblotzki.breaker;

import com.netflix.config.DynamicPropertyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring-Konfiguration und starten der Anwendung.
 *
 */
@Configuration
@EnableAutoConfiguration
public class App {

  static {
    System.setProperty(DynamicPropertyFactory.ENABLE_JMX, "true");
  }

  @Bean
  public Service getServiceImpl() {
    return new ServiceImpl();
  }

  @Bean
  public Service getHystraxService() {
    return new HystrixServiceImpl(getServiceImpl());
  }

  @Bean
  public Controller getController() {
    final Controller controller = new Controller();
    controller.setService(getHystraxService());
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
