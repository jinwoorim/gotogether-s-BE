package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
@JsonFormat
public enum GenderGroup {

    FEMALE_ONLY("여자끼리"),
    MALE_ONLY("남자끼리"),
    NO_MATTER("상관 없음");

    @JsonValue
    private final String koreanName;

    public static GenderGroup typeChecker(String koreanName) {
        return
                Arrays.stream(GenderGroup.values())
                        .filter(genderGroup -> genderGroup.getKoreanName().equals(koreanName))
                        .findAny()
                        .orElse(null);
    }
}
