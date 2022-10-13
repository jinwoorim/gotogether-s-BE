package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
@JsonFormat
public enum Continent {

    SOUTH_EAST_ASIA("동남아시아"),
    GUAM_AND_SAIPAN_AND_HAWAII("괌,사이판,하와이"),
    AUSTRALIA_AND_NEW_ZEALAND("호주,뉴질랜드"),

    INDIA_AND_MARGINAL_STATE("인도,주변국"),
    CENTRAL_ASIA("중앙아시아"),

    SOUTHEAST_AFRICA("동남아프리카"),
    NORTH_AFRICA_AND_MIDDLE_EAST("북아프리카,중동"),

    CAUCASUS("코카서스"),
    EUROPE("유럽"),

    CENTRAL_SOUTH_AMERICA("중남미"),
    NORTH_AMERICA("북미"),

    TAIWAN("대만"),
    CHINA("중국"),
    JAPAN("일본");

    @JsonValue
    private final String koreanName;

    public static Continent typeChecker(String koreanName) {
        return
                Arrays.stream(Continent.values())
                        .filter(continent -> continent.getKoreanName().equals(koreanName))
                        .findAny()
                        .orElse(null);
    }

    public static List<String> getContinentList(){
        Continent continent[] = Continent.values();
        List<String> continentValues = new ArrayList<>();
        for( Continent c : continent){
            continentValues.add(c.getKoreanName());
        }
        return continentValues;
    }
}
