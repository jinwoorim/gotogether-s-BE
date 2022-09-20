package com.gotogether.gotogethersbe.web.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultRes<T> {

    private int statusCode;
    private String responseMessage;
    private T data;

    /* 메소드에서 메소드를 호출하는 형태에서 직관적으로 변경하였고 굳이 두 가지 방식을
    사용할 이유를 느끼지 못해서 빌더 생성자만 냅뒀습니다.
     */

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
