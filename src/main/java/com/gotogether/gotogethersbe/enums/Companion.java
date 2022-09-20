package com.gotogether.gotogethersbe.enums;

public enum Companion {

    SOLO("나홀로 참가"),
    FRIENDS("친구나동료"),
    COUPLE("연인이나부부"),
    FAMILY("자녀를 동반하는 가족"),
    NO_MATTER("상관없음");

    private final String koreanName;

    Companion(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return  koreanName;
    }
}
