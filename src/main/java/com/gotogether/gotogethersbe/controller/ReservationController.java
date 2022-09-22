package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.dto.ReservationDto;
import com.gotogether.gotogethersbe.service.ReservationService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    // 예약하기
    @PostMapping("/reservations")
    public DefaultRes doReservation(@RequestBody ReservationDto.ReservationRequest request){

        reservationService.doReservation(request);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.RESERVATION_SUCCESS);
    }

    // 예약 상품 목록 조회
    @GetMapping("/reservations")
    public DefaultRes getReservationList(){

        return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_RESERVATION_LIST, reservationService.getReservationList());
    }

    // 예약 상태(대기,예약완료,취소) 수정

    @PutMapping("/reservations/status")
    public DefaultRes updateReservationStatus(@RequestBody ReservationDto.UpdateReservationStatusRequest request) {

        reservationService.updateReservationStatus(request);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_RESERVATION_STATUS);
    }

    // 예약 상품 삭제

}
