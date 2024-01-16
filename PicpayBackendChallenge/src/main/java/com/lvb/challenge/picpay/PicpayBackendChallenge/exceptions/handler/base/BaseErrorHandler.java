package com.lvb.challenge.picpay.PicpayBackendChallenge.exceptions.handler.base;

import com.lvb.challenge.picpay.PicpayBackendChallenge.exceptions.message.base.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;

public abstract class BaseErrorHandler {

    public ResponseEntity<Object> buildResponseEntity(final HttpStatus status, final Exception ex) {

        var apiError = ApiError.builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .errorType(ex.getClass().getSimpleName())
                .debugMessage(Arrays.toString(ex.getStackTrace()))
                .build();

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}
