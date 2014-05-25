package io.github.uvpoblotzki.breaker;

/**
 * Ein kleiner Service, dessen Aufgabe es ist alle, die auf ihn zugreifen, auszubremsen.
 */
public interface Service {

  /**
   * Stoppt die Welt und gibt zurück ob er es getan hat, in der Regel also {@code true}.
   *
   * @return ob die Welt angehalten wurde.
   */
  boolean stopTheWorld();

}
