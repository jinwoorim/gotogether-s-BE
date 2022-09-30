package com.gotogether.gotogethersbe.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@JsonFormat
@RequiredArgsConstructor
public enum GenderGroup {

    FEMALE_ONLY("여자끼리"),
    MALE_ONLY("남자끼리"),
    NO_MATTER("상관없음");

    @JsonValue
    private final String koreanName;
}
