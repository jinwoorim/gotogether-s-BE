package com.gotogether.gotogethersbe.domain.enums;

public enum Ages {

    TEENAGERS("10대"),
    TWENTIES("20대"),
    THIRTIES("30대"),
    FOURTIES("40대"),
    FIFTIES("50대"),
    SIXTIES("60대"),
    SEVENTIES_AND_OVER("70대이상"),
    NO_MATTER("상관없음");

    private final String koreanName;

    Ages(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return  koreanName;
    }
}
