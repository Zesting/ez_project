package Ezen.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Ezen.project.DTO.CheckDTO;
import Ezen.project.DTO.ReservationDTO;
import Ezen.project.domain.Reservation;
import Ezen.project.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @RequestMapping(value = "/verificationUser", method = RequestMethod.GET)
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
    public String reservationSelectDateView() {
        return "Reservation/SelectDate";
    }

    // 예약 날짜 저장 로직(rest)
    // @PostMapping("/Reservations/SelectDate")
    // public String reservationSelectDate(HttpSession session, @ModelAttribute
    // CheckDTO checkDTO) {
    // System.out.println("PostMapping 실행 -> checkDTO : " + checkDTO);
    // session.setAttribute("checkDTO", checkDTO);
    // return "redirect:SelectRoom";
    // }

    // 예약 룸
    @GetMapping("/Reservations/SelectRoom")
    public String reservationSelectRoomView(HttpSession session, Model model) {
        System.out.println("GetMapping 실행(/selectRoom)");
        CheckDTO checkDTO = (CheckDTO) session.getAttribute("checkDTO");
        List<?> roomList = reservationService.bookableList(checkDTO);
        System.out.println(roomList);

        model.addAttribute("checkDTO", checkDTO);
        model.addAttribute("roomList", roomList);

        return "Reservation/SelectRoom";
    }

    // rest로
    // @PostMapping("/Reservations/SelectRoom")
    // public String reservationSelectRoom(HttpSession session, Model model, RoomDTO
    // roomDTO) {
    // System.out.println("PostMapping(reservationSelectRoom) start");
    // System.out.println(roomDTO);
    // CheckDTO checkDTO = (CheckDTO) session.getAttribute("checkDTO");
    // Long userId = (Long) session.getAttribute("userId");
    //
    // Reservation reservation = reservationService.addReservation(userId,
    // roomDTO.getRoomId(), checkDTO);
    //
    // session.setAttribute("payReservation", reservation);// 예약 정보 세션에 저장해서 가져가기
    // return "redirect:/payment";
    // }

    @GetMapping(value = "/modify/{id}")
    public String modifyView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("reservation", reservationService.findOneReservationById(id).get());
        return "Reservation/modifyView";
    }

    @PostMapping(value = "/modify/{id}")
    public String modifyReservation(@PathVariable("id") Long id, ReservationDTO reservationDTO) {
        Optional<Reservation> reservation = reservationService.findOneReservationById(id);
        if (reservation.isPresent()) {
            reservationService.modifyReservation(reservationDTO);
        }
        return "redirect:/reservation/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteReservation(@PathVariable("id") Long id) {
        reservationService.verificationReservation(id);
        reservationService.dropReservation(id);
        return "redirect:/reservation/list";
    }

}
