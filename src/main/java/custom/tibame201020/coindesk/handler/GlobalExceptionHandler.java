package custom.tibame201020.coindesk.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException) {
        return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
