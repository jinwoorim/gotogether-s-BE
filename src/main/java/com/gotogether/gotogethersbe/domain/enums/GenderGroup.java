package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
@JsonFormat
public enum GenderGroup implements EnumCuration{

    FEMALE_ONLY("여자끼리"),
    MALE_ONLY("남자끼리"),
    NO_MATTER("상관 없음");

    @JsonValue
    private final String koreanName;

    @Override
    public GenderGroup TypeChecker(String koreanName) {
        return
                Arrays.stream((GenderGroup.values()))
                        .filter(genderGroup -> genderGroup.getKoreanName().equals(koreanName))
                        .findAny()
                        .orElse(null);
    }
}
