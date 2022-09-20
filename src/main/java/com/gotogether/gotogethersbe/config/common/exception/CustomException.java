package com.gotogether.gotogethersbe.config.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final String errorMessage;
    private final int statusCode;
}
