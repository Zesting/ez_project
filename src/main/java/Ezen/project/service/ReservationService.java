package Ezen.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Ezen.project.domain.Reservation;
import Ezen.project.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    // 예약 추가 기능 [회원 파트](비지니스 로직)
    @Transactional
    public Long reservationJoin(Reservation reservation) {
        reservationRepository.save(reservation);
        System.out.println("예약 추가 기능 확인 (저장된 예약 -> " + reservation + " )");
        return reservation.getReservationId();
    }

    // 저장된 모든 예약 출력 기능[관리자 파트](비지니스 로직)
    @Transactional(readOnly = true)
    public List<Reservation> findAllReservation() {
        return reservationRepository.findAll();
    }

    // 입력된 회원의 모든 예약 출력 기능[회원 파트 : 파라미터(userId)](비지니스 로직)
    @Transactional(readOnly = true)
    public List<Reservation> findAllReservationByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findAll().stream()
                .filter(u -> u.getUserId().equals(userId))
                .collect(Collectors.toList());
        System.out.println("입력된 유저에 대한 모든 예약 리스트 출력 : " + reservations);
        return reservations;
    }

    // 회원이 선택한 하나의 예약 출력 기능[회원 파트 : 파라미터(reservationId)](비지니스 로직)
    @Transactional(readOnly = true)
    public Optional<Reservation> findOneReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    // 예약 변경 기능[회원 파트](비지니스 로직)
    @Transactional
    public Optional<Reservation> modifyReservation(Reservation reservation) {
        System.out.println("예약 객체 정보 변경 시작");
        if (!(reservation == null)) {
            Optional<Reservation> reservationOptional = reservationRepository.findById(reservation.getReservationId());
            if (reservationOptional.isPresent()) {
                reservationRepository.save(reservation);
                return Optional.of(reservation);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    // 예약 삭제 기능[회원 파트](비지니스 로직)
    @Transactional
    public void dropReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
        System.out.println("예약 삭제 완료 기능 확인");
    }

    public Long verificationReservation(Long reservationId) {
        Optional<Reservation> dropReservation = reservationRepository.findById(reservationId);
        if (dropReservation.isPresent()) {
            return dropReservation.get().getReservationId();
        } else {
            return null;
        }
    }
}
