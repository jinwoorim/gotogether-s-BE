package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.ReservationPerson;
import lombok.Data;

public class ReservationPersonDto {

    // 예약자 목록 조회
    @Data
    public static class ReservationPersonListResponse {

        private String name;
        private String phoneNumber;
        private Boolean role;

        public ReservationPersonListResponse(ReservationPerson reservationPerson) {

            name = reservationPerson.getName();
            phoneNumber = reservationPerson.getPhoneNumber();
            role = reservationPerson.getRole();
        }
    }
}
