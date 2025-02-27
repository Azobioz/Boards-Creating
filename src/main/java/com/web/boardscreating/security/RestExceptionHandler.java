package com.web.boardscreating.security;

import com.web.boardscreating.dto.ErrorDto;
import com.web.boardscreating.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class}) // будет использован если будет исключение AppException
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAppException(AppException ex) { //метод применяться  для каждого контроллера, сработает, если будет исключение
        return ResponseEntity.status(ex.getCode())
                .body(ErrorDto.builder().message(ex.getMessage()).build());
    }

}
