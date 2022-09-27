package com.gotogether.gotogethersbe.domain.enums;

public enum Continent {

    SOUTH_EAST_ASIA_AND_PACIFIC("동남아/태평양"),
    INDIA_AND_CENTRAL_ASIA("인도/중앙아시아"),
    AFRICA_AND_MIDDLE_EAST("아프리카/중동"),
    EUROPE_AND_CAUCASUS("유럽/코카서스"),
    CENTRAL_SOUTH_AMERICA_AND_NORTH_AMERICA("중남미/북미"),
    TAIWAN_AND_CHINA_AND_JAPAN("대만/중국/일본");

    private final String koreanName;

    Continent(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return  koreanName;
    }
}
