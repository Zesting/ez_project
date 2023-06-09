package Ezen.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
