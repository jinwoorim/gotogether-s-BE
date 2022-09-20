package com.gotogether.gotogethersbe.enums;

public enum Religion {

    JESUS("하나님과 함께하는 여행"),
    BUDDHA("부처님의 발자취를 찾아"),
    NO_MATTER("상관없음");

    private final String koreanName;

    Religion(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return  koreanName;
    }
}
