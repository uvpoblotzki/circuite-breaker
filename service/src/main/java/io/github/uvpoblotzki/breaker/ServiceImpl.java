package io.github.uvpoblotzki.breaker;

import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementierung des Service. Es wird für eine bestimmte Zeit gewartet und der
 * Thread blockiert.
 */
public class ServiceImpl implements Service {

  private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

  private final DynamicLongProperty blockingDuration =
      DynamicPropertyFactory.getInstance().getLongProperty("service.blocking-duration", 30);

  /**
   * Diese Methode lässt keine andere Abfrage durch.
   * @return immer {@code true}
   */
  @Override
  public synchronized boolean stopTheWorld() {
    try {
      Thread.sleep(blockingDuration.get());
    } catch (final InterruptedException e) {
      logger.info("Service wurde unterbrochen");
    }
    return true;
  }
}
