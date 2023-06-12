package Ezen.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Ezen.project.DTO.CheckDTO;
import Ezen.project.domain.Reservation;
import Ezen.project.domain.User;
import Ezen.project.service.ReservationService;
import Ezen.project.service.RoomService;
// import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    // private final UserService userService;
    private final RoomService roomService;

    // 예약 리스트 뷰 메서드
    @GetMapping("/list")
    public String reservationListView(Model model) {
        List<Reservation> reservationList = reservationService.findAllReservation();
        model.addAttribute("reservationList", reservationList);
        System.out.println("reservationList : " + reservationList);
        return "Reservation/reservationListView";
    }

    // 예약 날짜 선택 뷰 메서드
    @GetMapping("/selectDate")
    public String reservationSelectDateView() {
        return "Reservation/reservationSelectDateView";
    }

    // 예약 날짜 저장 로직
    @PostMapping("selectDate")
    public String reservationSelectDate(HttpSession session, @ModelAttribute CheckDTO checkDTO) {
        System.out.println("PostMapping 실행 -> checkDTO : " + checkDTO);
        session.setAttribute("checkDTO", checkDTO);
        return "redirect:/";
    }

    // 예약 룸
    @GetMapping("/selectRoom")
    public String reservationSelectRoomView(HttpSession session, Model model) {
        CheckDTO checkDTO = (CheckDTO) session.getAttribute("checkDTO");
        System.out.println(
                "ReservationSelectRoom(GetMapping) -> checkDTO" + checkDTO.getCheckIn() + checkDTO.getCheckOut());
        model.addAttribute("checkDTO", checkDTO);
        return "Reservation/reservationSelectRoomView";
    }

    @PostMapping("/selectRoom")
    public String reservationSelectRoom(HttpSession session, Model model, CheckDTO checkDTO) {
        User user = (User) session.getAttribute("?");
        CheckDTO finalDTO = (CheckDTO) session.getAttribute("checkDTO");

        Reservation reservation = new Reservation();

        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        reservation.setUserId(user.getId());
        reservation.setCheckIn(finalDTO.getCheckIn());
        reservation.setCheckOut(finalDTO.getCheckOut());
        reservation.setRoomId(checkDTO.getRoomId());
        reservation.setReservationDate(dateFormat.format(today));
        reservation.setFinalPrice((int) ((roomService.findRoomById(checkDTO.getRoomId()).get().getRoomPrice()) *
                0.9));
        System.out.println("saveReservation -> " + reservation);
        reservationService.reservationJoin(reservation);
        return "redirect : /";
    }

}
