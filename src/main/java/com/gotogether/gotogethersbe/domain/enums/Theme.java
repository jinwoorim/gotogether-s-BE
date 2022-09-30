package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@JsonFormat
@RequiredArgsConstructor
public enum Theme {

    CULTURE("문화탐방"),
    GOLF("골프여행"),
    HEALING("휴양지"),
    ADVANTURE("오지탐험"),
    TRACKING("트레킹여행"),
    VOLUNTEER("봉사활동"),
    HOLYLAND("성지순례");

    @JsonValue
    private final String koreanName;
}
