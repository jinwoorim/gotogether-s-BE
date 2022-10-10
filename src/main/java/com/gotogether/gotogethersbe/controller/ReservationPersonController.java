package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.service.ReservationPersonService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationPersonController {

    private final ReservationPersonService reservationPersonService;

    @GetMapping("/reservation-people/{reservationId}")
    public DefaultRes getReservationPersonList(@PathVariable Long reservationId) {

        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_RESERVATION_PERSON_LIST, reservationPersonService.getReservationPersonList(reservationId));
    }


}
