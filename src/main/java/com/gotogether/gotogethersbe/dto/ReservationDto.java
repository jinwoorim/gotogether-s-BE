package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Reservation;
import com.gotogether.gotogethersbe.domain.enums.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

public class ReservationDto {

    // 예약하기
    @Data
    public static class ReservationRequest {

        private Long product_id;
        private long totalPrice;
    }

    // 예약 상품 목록 조회
    @Data
    public static class ReservationListResponse {

        private Long reservation_id;
        private long totalPrice;

        @Enumerated(EnumType.STRING)
        private Status status;

        private LocalDate reservationDate;

        private String reservationDayOfWeek;

        public ReservationListResponse(Reservation reservation){
            reservation_id = reservation.getId();
            totalPrice = reservation.getTotalPrice();
            status = reservation.getStatus();
            reservationDate = reservation.getReservationDate();
            reservationDayOfWeek = reservation.getReservationDayOfWeek();
        }
    }

    // 예약 상품 상세 조회
    @Data
    public static class ReservationDetailResponse {

        private Long reservation_id;
        private long totalPrice;

        @Enumerated(EnumType.STRING)
        private Status status;

        private LocalDate reservationDate;
        private String reservationDayOfWeek;
        public ReservationDetailResponse(Reservation reservation){
            reservation_id = reservation.getId();
            totalPrice = reservation.getTotalPrice();
            status = reservation.getStatus();
            reservationDate = reservation.getReservationDate();
            reservationDayOfWeek = reservation.getReservationDayOfWeek();
        }
    }

    // 예약 상태(예약대기, 예약완료, 예약취소) 수정
    @Data
    public static class UpdateReservationStatusRequest {

        private Long reservation_id;
        private Status status;
    }

    // 에약 상품 삭제
    @Data
    public static class DeleteReservationRequest {

        private Long reservation_id;
    }
}
