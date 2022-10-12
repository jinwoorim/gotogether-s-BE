package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
@JsonFormat
public enum Theme {

    CULTURE("문화탐방"),
    GOLF("골프여행"),
    HEALING("리조트 휴양 및 힐링"),
    ADVANTURE("오지탐험"),
    TRACKING("트레킹여행"),
    VOLUNTEER("봉사활동"),
    HOLYLAND("성지순례"),
    NO_MATTER("상관 없음");

    @JsonValue
    private final String koreanName;

    public static Theme typeChecker(String koreanName) {
        return
                Arrays.stream(Theme.values())
                        .filter(theme -> theme.getKoreanName().equals(koreanName))
                        .findAny()
                        .orElse(null);
    }
}