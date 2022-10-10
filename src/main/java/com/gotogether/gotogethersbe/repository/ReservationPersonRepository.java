package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.ReservationPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationPersonRepository extends JpaRepository<ReservationPerson, Long> {

    List<ReservationPerson> findByReservation_id(Long id);
}
