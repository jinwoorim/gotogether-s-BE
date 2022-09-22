package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.domain.Reservation;
import com.gotogether.gotogethersbe.dto.ReservationDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    // 예약하기
    @Transactional
    public void doReservation(ReservationDto.ReservationRequest request){

        Reservation reservation = Reservation.builder()
                .product(null)
                .member(memberRepository.findById(SecurityUtil.getCurrentMemberId()).get())
                .totalPrice(request.getTotalPrice())
                .status(request.getStatus())
                .build();

        reservationRepository.save(reservation);
    }

    // 예약 상품 목록 조회
    public List<ReservationDto.ReservationResponse> getReservationList() {

        return mapToDto(reservationRepository.findByMember_id(SecurityUtil.getCurrentMemberId()));
    }

    private List<ReservationDto.ReservationResponse> mapToDto(List<Reservation> reservationList) {

        return reservationList
                .stream()
                .map(ReservationDto.ReservationResponse::new)
                .toList();
    }

    // 예약 상태(대기,예약완료,취소) 수정
    public void updateReservationStatus(ReservationDto.UpdateReservationStatusRequest request) {

        Reservation reservation = reservationRepository.findById(request.getReservation_id()).orElseThrow(NoSuchElementException::new);

        reservation.setStatus(request.getStatus());

        reservationRepository.save(reservation);
    }

    // 예약 상품 삭제
    public void deleteReservation(ReservationDto.UpdateReservationStatusRequest request) {

        Reservation reservation = reservationRepository.findById(request.getReservation_id()).orElseThrow(NoSuchElementException::new);

        reservationRepository.delete(reservation);
    }
}
