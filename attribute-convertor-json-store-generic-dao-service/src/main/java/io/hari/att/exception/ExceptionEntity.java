package io.hari.att.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ExceptionEntity {
    String message;
    Throwable cause;
    HttpStatus httpStatus;
    String path;
    LocalDateTime time;
    String stackTrace;
}
