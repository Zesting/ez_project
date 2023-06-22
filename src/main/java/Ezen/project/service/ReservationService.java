package Ezen.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Ezen.project.DTO.CheckDTO;
import Ezen.project.DTO.ReservationDTO;
import Ezen.project.domain.Reservation;
import Ezen.project.domain.Room;
import Ezen.project.repository.ReservationRepository;
import Ezen.project.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    // 예약 추가 기능 [회원 파트](비지니스 로직)
    @Transactional
    public Long reservationJoin(Reservation reservation) {
        reservationRepository.save(reservation);
        System.out.println("예약 추가 기능 확인 (저장된 예약 -> " + reservation + " )");
        return reservation.getReservationId();
    }

    @Transactional
    public Reservation addReservation(Long userId, Long roomId, CheckDTO checkDTO) {
        LocalDate today = LocalDate.now();
        Date date = Date.valueOf(today);

        Date checkIn = checkDTO.getCheckIn();
        Date checkOut = checkDTO.getCheckOut();

        java.util.Date utilCheckIn = new java.util.Date(checkIn.getTime());
        java.util.Date utilCheckOut = new java.util.Date(checkOut.getTime());

        // Calculate the difference in milliseconds
        long diffMilliseconds = utilCheckOut.getTime() - utilCheckIn.getTime();

        // Convert milliseconds to days
        long diffDays = diffMilliseconds / (24 * 60 * 60 * 1000);

        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setRoomId(roomId);
        reservation.setCheckIn(checkDTO.getCheckIn());
        reservation.setCheckOut(checkDTO.getCheckOut());
        reservation.setReservationDate(date);
        reservation.setFinalPrice((int) ((roomRepository.findById(roomId).get().getRoomPrice() * diffDays) * 0.9));
        reservationRepository.save(reservation);

        return reservation;

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

    @Transactional
    // check_In 날짜에 해당하는 룸 조회 기능[관리자 파트]
    public List<Reservation> findAllReservationByCheckIn(Date checkIn) {
        return reservationRepository.findAll().stream()
                .filter(r -> r.getCheckIn().toLocalDate().equals(checkIn.toLocalDate()))
                .collect(Collectors.toList());
    }

    // check_Out 날짜 이전에 해당하는 룸 조회 기능[관리자 파트]
    @Transactional
    public List<Reservation> findAllReservationByCheckOut(Date checkOut) {
        LocalDateTime checkOutDateTime = checkOut.toLocalDate().atStartOfDay();
        LocalDate previousDate = checkOutDateTime.toLocalDate().minusDays(1);

        return reservationRepository.findAll().stream()
                .filter(r -> r.getCheckIn().toLocalDate().equals(previousDate))
                .collect(Collectors.toList());
    }

    // 예약 변경 기능[회원 파트](비지니스 로직)
    @Transactional
    public Optional<Reservation> modifyReservation(ReservationDTO reservationDTO) {
        System.out.println("예약 객체 정보 변경 시작");
        if (!(reservationDTO == null)) {
            Optional<Reservation> reservationOptional = reservationRepository
                    .findById(reservationDTO.getReservationId());
            if (reservationOptional.isPresent()) {
                Reservation reservation = converter(reservationDTO);
                reservationRepository.save(reservation);
                return Optional.of(reservation);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public Reservation converter(ReservationDTO reservationDTO) {

        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationDTO.getReservationId());
        reservation.setRoomId(reservationDTO.getRoomId());
        reservation.setUserId(reservationDTO.getUserId());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setCheckIn(reservationDTO.getCheckIn());
        reservation.setCheckOut(reservationDTO.getCheckOut());
        reservation.setFinalPrice(reservationDTO.getFinalPrice());
        return reservation;
    }

    // 예약 삭제 기능[회원 파트](비지니스 로직)
    @Transactional
    public void dropReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
        System.out.println("예약 삭제 완료 기능 확인");
    }

    // 예약 검증 비지니스 로직
    public Long verificationReservation(Long reservationId) {
        Optional<Reservation> dropReservation = reservationRepository.findById(reservationId);
        if (dropReservation.isPresent()) {
            return dropReservation.get().getReservationId();
        } else {
            return null;
        }
    }

    // 사용자가 선택한 날짜에 예약 가능한 방 출력 비지니스 로직(서비스)
    @Transactional
    public List<?> bookableList(CheckDTO checkDTO) { // ? -> roomList
        List<Room> bookableRooms = roomRepository.findAll();
        List<Reservation> checkInReservation = findAllReservationByCheckIn(checkDTO.getCheckIn());
        List<Room> roomsToRemove1 = new ArrayList<>();
        for (Reservation reservation : checkInReservation) {
            for (Room checkOnRoom : bookableRooms) {
                if (checkOnRoom.getRoomId().equals(reservation.getRoomId())) {
                    roomsToRemove1.add(checkOnRoom);
                }
            }
        }
        List<Reservation> checkOutReservation = findAllReservationByCheckOut(checkDTO.getCheckOut());
        List<Room> roomsToRemove2 = new ArrayList<>();
        for (Reservation reservation : checkOutReservation) {
            for (Room checkOnRoom : bookableRooms) {
                if (checkOnRoom.getRoomId().equals(reservation.getRoomId())) {
                    roomsToRemove2.add(checkOnRoom);
                }
            }
        }

        bookableRooms.removeAll(roomsToRemove1);
        bookableRooms.removeAll(roomsToRemove2);
        return bookableRooms;
    }

}
