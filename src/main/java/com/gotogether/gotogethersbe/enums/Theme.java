package com.gotogether.gotogethersbe.enums;

public enum Theme {

    CULTURE("문화탐방"),
    GOLF("골프여행"),
    HEALING("휴양지"),
    ADVANTURE("오지탐험"),
    TRACKING("트레킹여행"),
    VOLUNTEER("봉사활동"),
    HOLYLAND("성지순례");

    private final String koreanName;

    Theme(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return  koreanName;
    }
}
