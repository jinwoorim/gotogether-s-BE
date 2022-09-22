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
    public static class ReservationResponse {

        private Long id;
        private long totalPrice;

        @Enumerated(EnumType.STRING)
        private Status status;

        private LocalDate createdDate;
        public ReservationResponse(Reservation reservation){
            id = reservation.getId();
            totalPrice = reservation.getTotalPrice();
            status = reservation.getStatus();
            createdDate = reservation.getCreatedDate();
        }
    }

    @Data
    public static class UpdateReservationStatusRequest {

        private Long reservation_id;
        private Status status;
    }
}
