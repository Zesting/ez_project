package Ezen.project.apiController;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Ezen.project.DTO.CheckDTO;
import Ezen.project.DTO.ReservationDTO;
import Ezen.project.DTO.RoomDTO;
import Ezen.project.domain.Reservation;
import Ezen.project.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiReservationController {

    private final ReservationService reservationService;

    @RequestMapping(value = "/Reservations/SelectDate", method = RequestMethod.POST)
    public ResponseEntity<?> selectDate(@RequestBody CheckDTO checkDTO, HttpSession session) {
        System.out.println("REST(POST)selectRoom 실행 -> checkDTO : checkDTO" + checkDTO);

        session.setAttribute("checkDTO", checkDTO);
        System.out.println("post selectDate Post : checkDTO : " + session.getAttribute("checkDTO"));
        return ResponseEntity.ok(checkDTO);
    }

    @RequestMapping(value = "/Reservations/SelectRoom", method = RequestMethod.POST)
    public ResponseEntity<?> selectRoom(@RequestBody RoomDTO roomDTO, HttpSession session, Model model) {
        System.out.println("PostMapping(reservationSelectRoom) start");
        System.out.println(roomDTO);
        CheckDTO checkDTO = (CheckDTO) session.getAttribute("checkDTO");

        Long userId = (Long) session.getAttribute("userId");

        Reservation reservation = reservationService.addReservation(userId, roomDTO.getRoomId(), checkDTO);
        session.setAttribute("payReservation", reservation); // 예약 정보 세션에 저장해서 가져가기

        return ResponseEntity.ok(reservation);
    }

    @RequestMapping(value = "/Reservations/modify/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyReservation(@PathVariable("id") Long id,
            @RequestBody ReservationDTO reservationDTO) {
        Optional<Reservation> reservation = reservationService.findOneReservationById(id);
        if (reservation.isPresent()) {
            Reservation result = reservationService.modifyReservation(reservationDTO).get();
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @RequestMapping(value = "/Reservations/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReservation(@PathVariable("id") Long id) {
        reservationService.verificationReservation(id);
        reservationService.dropReservation(id);
        return ResponseEntity.ok(id);
    }

}
