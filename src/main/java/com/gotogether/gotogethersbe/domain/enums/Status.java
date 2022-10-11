package com.gotogether.gotogethersbe.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {

    STANDBY("대기"),
    CANCEL("취소"),
    COMPLETE("예약완료");

    private final String koreanName;
}
