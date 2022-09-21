package com.gotogether.gotogethersbe.web.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultRes<T> {

    private int statusCode;
    private String responseMessage;
    private T data;

    public static<T> DefaultRes<T> res(final int statusCode, final String responseMessage) {
        return DefaultRes.<T>builder()
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }

    public static<T> DefaultRes<T> res(final int statusCode, final String responseMessage, final T t) {
        return DefaultRes.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }
}
