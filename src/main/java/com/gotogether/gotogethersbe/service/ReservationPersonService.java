package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.domain.ReservationPerson;
import com.gotogether.gotogethersbe.dto.ReservationPersonDto;
import com.gotogether.gotogethersbe.repository.ReservationPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationPersonService {

    private final ReservationPersonRepository reservationPersonRepository;

    // 예약자 목록 조회
    @Transactional(readOnly = true)
    public List<ReservationPersonDto.ReservationPersonListResponse> getReservationPersonList(Long reservationId) {

        return mapToDto(reservationPersonRepository.findByReservation_id(reservationId));
    }

    private List<ReservationPersonDto.ReservationPersonListResponse> mapToDto(List<ReservationPerson> reservationPersonList) {

        return reservationPersonList
                .stream()
                .map(ReservationPersonDto.ReservationPersonListResponse::new)
                .toList();
    }
}
