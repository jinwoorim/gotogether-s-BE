package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByMember_id(Long id);
}
