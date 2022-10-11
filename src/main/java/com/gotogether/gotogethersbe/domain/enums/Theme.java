package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
@Getter
@RequiredArgsConstructor
public enum Theme implements EnumCuration {

    CULTURE("문화탐방"),
    GOLF("골프여행"),
    HEALING("휴양지"),
    ADVANTURE("오지탐험"),
    TRACKING("트레킹여행"),
    VOLUNTEER("봉사활동"),
    HOLYLAND("성지순례"),
    NO_MATTER("상관 없음");

    private final String koreanName;

    @Override
    public Theme TypeChecker(String koreanName) {
        return
                Arrays.stream((Theme.values()))
                        .filter(theme -> theme.getKoreanName().equals(koreanName))
                        .findAny()
                        .orElse(null);
    }
}