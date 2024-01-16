package com.lvb.challenge.picpay.PicpayBackendChallenge.exceptions.handler;

import com.lvb.challenge.picpay.PicpayBackendChallenge.exceptions.handler.base.BaseErrorHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler extends BaseErrorHandler {

    @ExceptionHandler(value = {NullPointerException.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return super.buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }


}
