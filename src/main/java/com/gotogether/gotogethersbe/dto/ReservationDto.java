package com.gotogether.gotogethersbe.dto;

import com.gotogether.gotogethersbe.domain.Reservation;
import com.gotogether.gotogethersbe.domain.enums.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

public class ReservationDto {

    @Data
    public static class ReservationRequest {

        private Long product_id;
        private long totalPrice;

        @Enumerated(EnumType.STRING)
        private Status status;

    }

    @Data
    public static class ReservationListResponse {

        private Long reservation_id;
        private long totalPrice;

        @Enumerated(EnumType.STRING)
        private Status status;

        private LocalDate reservationDate;

        public ReservationListResponse(Reservation reservation){
            reservation_id = reservation.getId();
            totalPrice = reservation.getTotalPrice();
            status = reservation.getStatus();
            reservationDate = reservation.getReservationDate();
        }
    }

    @Data
    public static class ReservationDetailResponse {

        private Long reservation_id;
        private long totalPrice;

        @Enumerated(EnumType.STRING)
        private Status status;

        private LocalDate reservationDate;
        public ReservationDetailResponse(Reservation reservation){
            reservation_id = reservation.getId();
            totalPrice = reservation.getTotalPrice();
            status = reservation.getStatus();
            reservationDate = reservation.getReservationDate();
        }
    }

    @Data
    public static class UpdateReservationStatusRequest {

        private Long reservation_id;
        private Status status;
    }
    @Data
    public static class DeleteReservationRequest {

        private Long reservation_id;
    }
}
