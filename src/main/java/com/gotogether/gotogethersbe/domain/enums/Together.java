package com.gotogether.gotogethersbe.domain.enums;

public enum Together {

    FEMALE_ONLY("여자끼리"),
    MALE_ONLY("남자끼리"),
    NO_MATTER("상관없음");

    private final String koreanName;

    Together(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return  koreanName;
    }
}
