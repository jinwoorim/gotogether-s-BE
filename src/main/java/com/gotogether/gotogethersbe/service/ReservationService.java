package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.common.exception.CustomException;
import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.domain.Reservation;
import com.gotogether.gotogethersbe.domain.ReservationPerson;
import com.gotogether.gotogethersbe.domain.enums.Status;
import com.gotogether.gotogethersbe.dto.ReservationDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.repository.ProductRepository;
import com.gotogether.gotogethersbe.repository.ReservationPersonRepository;
import com.gotogether.gotogethersbe.repository.ReservationRepository;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationPersonRepository reservationPersonRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 예약하기
    @Transactional
    public void doReservation(ReservationDto.ReservationRequest request) {

        Reservation reservation = Reservation.builder()
                .product(productRepository.findById(request.getProduct_id())
                        .orElseThrow(() -> new CustomException(ResponseMessage.NOT_FOUND_PRODUCT, StatusCode.NOT_FOUND)))
                .member(memberRepository.findById(SecurityUtil.getCurrentMemberId()).get())

                .totalReservationPeople(request.getReservation().getTotalReservationPeople())
                .totalBasicPrice(request.getReservation().getTotalBasicPrice())

                .firstSelectOption(request.getReservation().getFirstSelectOption())
                .totalFirstSelectOptionCount(request.getReservation().getTotalFirstSelectOptionCount())
                .totalFirstSelectOptionPrice(request.getReservation().getTotalFirstSelectOptionPrice())

                .secondSelectOption(request.getReservation().getSecondSelectOption())
                .totalSecondSelectOptionCount(request.getReservation().getTotalSecondSelectOptionCount())
                .totalSecondSelectOptionPrice(request.getReservation().getTotalSecondSelectOptionPrice())

                .thirdSelectOption(request.getReservation().getThirdSelectOption())
                .totalThirdSelectOptionCount(request.getReservation().getTotalThirdSelectOptionCount())
                .totalThirdSelectOptionPrice(request.getReservation().getTotalThirdSelectOptionPrice())

                .totalPrice(request.getReservation().getTotalPrice())
                .status(Status.STANDBY)
                .duration(request.getReservation().getDuration())

                .build();

        Reservation getReservation = reservationRepository.save(reservation);

        List<ReservationPerson> reservationPersonList = new ArrayList<>();

        for(ReservationPerson reservationPerson : request.getReservationPersonList()) {

                ReservationPerson addReservationPerson = ReservationPerson.builder()
                        .name(reservationPerson.getName())
                        .phoneNumber(reservationPerson.getPhoneNumber())
                        .role(reservationPerson.getRole())
                        .reservation(getReservation)
                        .build();

                reservationPersonList.add(addReservationPerson);
        }

        reservationPersonRepository.saveAll(reservationPersonList);
    }

    // 예약 상품 목록 조회
    @Transactional(readOnly = true)
    public List<ReservationDto.ReservationListResponse> getReservationList() {

        return mapToDto(reservationRepository.findByMember_idOrderByIdDesc(SecurityUtil.getCurrentMemberId()));
    }

    // 최근 예약 상품 기간별 필터링(90일, 180일, 365일)
    @Transactional(readOnly = true)
    public List<ReservationDto.ReservationListResponse> getReservationByPeriod(int period) {

        List<Reservation> list = reservationRepository.findByMember_idOrderByIdDesc(SecurityUtil.getCurrentMemberId());

        List<Reservation> recentReservationByPeriod = new ArrayList<>();

        for (Reservation reservation : list) {

            if(period >= Period.between(LocalDate.now(), reservation.getReservationDate()).getDays()) {

                recentReservationByPeriod.add(reservation);
            }
        }
        return mapToDto(recentReservationByPeriod);
    }

    private List<ReservationDto.ReservationListResponse> mapToDto(List<Reservation> reservationList) {

        return reservationList
                .stream()
                .map(ReservationDto.ReservationListResponse::new)
                .toList();
    }

    // 예약 상품 상세 조회
    @Transactional(readOnly = true)
    public ReservationDto.ReservationDetailResponse getReservation(Long id) {

        return new ReservationDto.ReservationDetailResponse(reservationRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseMessage.NOT_FOUND_RESERVATION, StatusCode.NOT_FOUND)));
    }

    // 예약 상태(예약대기,예약완료,예약취소) 수정
    @Transactional
    public void updateReservationStatus(ReservationDto.UpdateReservationStatusRequest request) {

        Reservation reservation = reservationRepository.findById(request.getReservation_id()).get();

        reservationRepository.save(reservation.updateReservationStatus(request.getStatus()));

    }

    // 예약 상품 삭제
    @Transactional
    public void deleteReservation(ReservationDto.UpdateReservationStatusRequest request) {

        reservationRepository.deleteById(request.getReservation_id());
    }
}
