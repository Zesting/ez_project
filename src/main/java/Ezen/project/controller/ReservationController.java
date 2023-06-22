package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Ezen.project.DTO.CheckDTO;
import Ezen.project.domain.Reservation;
import Ezen.project.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @RequestMapping(value = "/Reservations/verificationUser", method = RequestMethod.GET)
    public String verification(HttpSession session, Model model) {
        session.getAttribute("userId");
        return "Reservation/verification";
    }

    // 예약 리스트 리턴
    @RequestMapping(value = "/Reservations", method = RequestMethod.GET)
    public String reservationListView(Model model) {
        List<Reservation> reservationList = reservationService.findAllReservation();
        model.addAttribute("reservationList", reservationList);
        System.out.println("reservationList : " + reservationList);
        return "Reservation/reservationListView";
    }

    // 예약 날짜 선택 뷰 메서드
    @RequestMapping(value = "/Reservations/SelectDate", method = RequestMethod.GET)
    public String reservationSelectDateView(@ModelAttribute CheckDTO checkDTO) {
        return "Reservation/SelectDate";
    }

    // 예약 룸 선택 뷰 메서드
    @RequestMapping(value = "/Reservations/SelectRoom", method = RequestMethod.GET)
    public String reservationSelectRoomView(Model model, HttpSession session) {

        System.out.println("GetMapping 실행(/selectRoom)");
        CheckDTO checkDTO = (CheckDTO) session.getAttribute("checkDTO");
        System.out.println("GetMApping CheckDTO :" + checkDTO);
        model.addAttribute("checkDTO", checkDTO);

        List<?> roomList = reservationService.bookableList(checkDTO);
        model.addAttribute("roomList", roomList);

        return "Reservation/SelectRoom";
    }

    @RequestMapping(value = "/Reservations/modify/{id}", method = RequestMethod.GET)
    public String modifyView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("reservation", reservationService.findOneReservationById(id).get());
        return "Reservation/modifyView";
    }

}
