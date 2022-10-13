package com.gotogether.gotogethersbe.config.common.exception;

import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //커스텀 예외 처리
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : ", e.getErrorMessage());
        return ErrorResponse.toResponseEntity(e.getErrorMessage(), e.getStatusCode());
    }

    //회원 가입 및 요청값 에러
    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> handleDataException() {
        log.error("handleDataException throw Exception : CREATED_FAIL");
        return ErrorResponse.toResponseEntity(ResponseMessage.REQUEST_ERROR, StatusCode.BAD_REQUEST);
    }

    //로그인 예외 처리
    @ExceptionHandler(value = {BadCredentialsException.class})
    protected ResponseEntity<ErrorResponse> handleLoginException() {
        log.error("handleDataException throw Exception : LOGIN_FAIL");
        return ErrorResponse.toResponseEntity("회원 비밀번호가 틀렸습니다.", StatusCode.UNAUTHORIZED);
    }
}