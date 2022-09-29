package com.gotogether.gotogethersbe.domain.enums;

public enum Continent {

    SOUTH_EAST_ASIA("동남아시아"),
    GUAM_AND_SAIPAN_AND_HAWAII("괌&사이판&하와이"),
    AUSTRALIA_AND_NEW_ZEALAND("호주&뉴질랜드"),

    INDIA_AND_MARGINAL_STATE("인도&주변국"),
    CENTRAL_ASIA("중앙아시아"),

    SOUTHEAST_AFRICA("동남아프리카"),
    NORTH_AFRICA_AND_MIDDLE_EAST("북아프리카&중동"),

    CAUCASUS("코카서스"),
    EUROPE("유럽"),

    CENTRAL_SOUTH_AMERICA("중남미"),
    NORTH_AMERICA("북미"),

    TAIWAN("대만"),
    CHINA("중국"),
    JAPAN("일본");

    private final String koreanName;

    Continent(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return  koreanName;
    }

}
