package Ezen.project.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import Ezen.project.domain.Reservation;
import Ezen.project.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    // 예약 추가 기능 (비지니스 로직)
    @Transactional
    public Long reservationJoin(Reservation reservation) {
        reservationRepository.save(reservation);
        System.out.println("예약 추가 기능 확인 (저장된 예약 -> " + reservation + " )");
        return reservation.getReservationId();
    }

    // 모든 예약 출력 기능
    @Transactional
    public List<Reservation> findAllReservation(Long userId) {
        return null;
    }

}
