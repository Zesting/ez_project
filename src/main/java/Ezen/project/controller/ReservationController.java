package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.domain.Reservation;
import Ezen.project.service.ReservationService;
import Ezen.project.service.RoomService;
import Ezen.project.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    // private final UserService userService;
    // private final RoomService roomService;

    @GetMapping("/reservation")
    public String reservationListView(Model model) {
        List<Reservation> reservationList = reservationService.findAllReservation();
        model.addAttribute("reservationList", reservationList);
        System.out.println("reservationList : " + reservationList);
        return "Reservation/reservationListView";
    }

    @GetMapping("/reservation/join")
    public String reservationJoinView() {
        return "Reservation/reservationJoinView";
    }

}
