package io.hari.att.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    // https://youtu.be/PzK4ZXa2Tbc
// https://dzone.com/articles/best-practice-for-exception-handling-in-spring-boo
    // https://zetcode.com/springboot/controlleradvice/ ****

    @ExceptionHandler(value = {IllegalStateException.class, RuntimeException.class, Exception.class})
    public ResponseEntity<Object> handleException(Exception e, WebRequest webRequest) {
        final String s = Arrays.toString(e.getStackTrace());
        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
                .message(e.getMessage())
                .cause(e.getCause())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .time(LocalDateTime.now())
                .stackTrace(s)
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<Object> handleException2(Exception ex) {
//        log.error("Exception: + 2 ", ex.getMessage());
//        final ExceptionEntity exceptionEntity = ExceptionEntity.builder()
//                .message(ex.getMessage())
//                .time(LocalDateTime.now())
//                .statusCode(500)
//                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//                .build();
//        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}

