package Ezen.project.apiController;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Ezen.project.DTO.CheckDTO;
import Ezen.project.DTO.RoomDTO;
import Ezen.project.domain.Reservation;
import Ezen.project.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiReservationController {

    private final ReservationService reservationService;

    @RequestMapping(value = "/Reservations/SelectDate", method = RequestMethod.POST)
    public ResponseEntity<?> selectDate(@RequestBody @Valid CheckDTO checkDTO, HttpSession session) {
        System.out.println("REST(POST)selectRoom 실행 -> checkDTO : checkDTO" + checkDTO);
        session.setAttribute("checkDTO", checkDTO);
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

}
