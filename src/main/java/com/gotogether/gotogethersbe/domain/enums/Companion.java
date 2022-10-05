package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@JsonFormat
@RequiredArgsConstructor
public enum Companion {

    SOLO("나홀로 참가"),
    FRIENDS("친구나 동료"),
    COUPLE("연인이나 부부"),
    FAMILY("자녀를 동반하는 가족"),
    NO_MATTER("상관 없음");

    @JsonValue
    private final String koreanName;
}
