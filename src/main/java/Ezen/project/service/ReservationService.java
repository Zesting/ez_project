package Ezen.project.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setRoomId(roomId);
        reservation.setCheckIn(checkDTO.getCheckIn());
        reservation.setCheckOut(checkDTO.getCheckOut());
        reservation.setReservationDate(dateFormat.format(today));
        reservation.setFinalPrice((int) (roomRepository.findById(roomId).get().getRoomPrice() * 0.9));
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

    // check_In 날짜에 해당하는 룸 조회 기능[관리자 파트]
    @Transactional(readOnly = true)
    public List<Reservation> findAllReservationByCheckIn(Date checkIn) {
        return reservationRepository.findAll().stream().filter(d -> d.getCheckIn().equals(checkIn)).toList();
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
        List<Reservation> checkOutReservation = findAllReservationByCheckIn(checkDTO.getCheckIn());

        List<Room> roomsToRemove = new ArrayList<>();
        for (Reservation reservation : checkOutReservation) {
            for (Room checkOnRoom : bookableRooms) {
                if (checkOnRoom.getRoomId().equals(reservation.getRoomId())) {
                    roomsToRemove.add(checkOnRoom);
                }
            }
        }
        bookableRooms.removeAll(roomsToRemove);
        return bookableRooms;
    }

}
