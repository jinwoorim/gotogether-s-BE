package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.ReservationPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationPersonRepository extends JpaRepository<ReservationPerson, Long> {

}
