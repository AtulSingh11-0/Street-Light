package ashis.com.backend.Street_light.advice;

import ashis.com.backend.Street_light.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler({
      AreaCodeAlreadyExistsException.class,
      TransformerCodeAlreadyExistsException.class
  })
  public ResponseEntity<Map<String, Object>> handleBadRequestExceptions(Exception e) {
    return ResponseEntity.badRequest().body(buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
  }

  @ExceptionHandler({
      AreaNotFoundException.class,
      StreetLightNotFoundException.class,
      TransformerNotFoundException.class,
      NoResourceFoundException.class
  })
  public ResponseEntity<Map<String, Object>> handleNotFoundExceptions(Exception e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()));
  }

  private Map<String, Object> buildErrorResponse(HttpStatus status, String message) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "error");
    response.put("status_code", status.value());
    response.put("message", message);
    return response;
  }
}
