package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat
public enum Status {

    STANDBY("예약대기"),
    CANCEL("예약취소"),
    COMPLETE("예약완료");
    @JsonValue
    private final String koreanName;

    Status(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return this.koreanName;
    }
}
