package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonFormat
public enum Status {

    STANDBY("대기"),
    CANCEL("취소"),
    COMPLETE("예약완료");

    @JsonValue
    private final String koreanName;
}
