package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonFormat
public enum Status {

    STANDBY("예약대기"),
    CANCEL("예약취소"),
    COMPLETE("예약완료");

    @JsonValue
    private final String koreanName;
}
