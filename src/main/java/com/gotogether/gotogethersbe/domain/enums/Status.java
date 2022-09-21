package com.gotogether.gotogethersbe.domain.enums;

public enum Status {

    STANDBY("대기"),
    CANCEL("취소"),
    COMPLETE("예약완료");

    private final String koreanName;

    Status(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return  koreanName;
    }
}
