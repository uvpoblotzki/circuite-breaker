package io.github.uvpoblotzki.breaker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Ein HTTP-Endpoint für den Service.
 */
@RequestMapping("/**")
public class Controller {

  private Service service;

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<String> handleGet() {
    final StringBuilder sb = new StringBuilder();
    final long start = System.currentTimeMillis();
    boolean success = false;
    if (getService() != null) {
      success = getService().stopTheWorld();
    }
    sb.append(success ? "OK": "FAIL");
    sb.append(" Duration: ");
    sb.append(System.currentTimeMillis() - start);
    sb.append(" ms");
    // Schlechter style, aber einfacher in der Auswertung zu erkennen, wann der Fallback greift.
    return new ResponseEntity<String>(sb.toString(), success ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }

  @SuppressWarnings("javadoc")
  public Service getService() {
    return service;
  }

  @SuppressWarnings("javadoc")
  public void setService(final Service service) {
    this.service = service;
  }
}
