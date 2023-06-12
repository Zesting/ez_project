package Ezen.project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Ezen.project.DTO.CheckDTO;
import Ezen.project.DTO.RoomDTO;
import Ezen.project.DTO.UserDTO;
import Ezen.project.domain.Reservation;
import Ezen.project.domain.Room;
import Ezen.project.service.ReservationService;
import Ezen.project.service.RoomService;
import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
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
    @PostMapping("/selectDate")
    public String reservationSelectDate(HttpSession session, @ModelAttribute CheckDTO checkDTO) {
        System.out.println("PostMapping 실행 -> checkDTO : " + checkDTO);
        session.setAttribute("checkDTO", checkDTO);
        return "redirect:selectRoom";
    }

    // 예약 룸
    @GetMapping("/selectRoom")
    public String reservationSelectRoomView(HttpSession session, Model model) {
        System.out.println("GetMapping 실행(/selectRoom)");
        CheckDTO checkDTO = (CheckDTO) session.getAttribute("checkDTO");
        model.addAttribute("checkDTO", checkDTO);
        List<Room> roomList = roomService.findAllRoom();
        List<Reservation> checkOutReservations = reservationService.findAllReservationByCheckIn(checkDTO.getCheckIn());

        List<Room> roomsToRemove = new ArrayList<>();
        for (Reservation reservation : checkOutReservations) {
            for (Room checkOnRoom : roomList) {
                if (checkOnRoom.getRoomId().equals(reservation.getRoomId())) {
                    roomsToRemove.add(checkOnRoom);
                }
            }
        }

        roomList.removeAll(roomsToRemove);
        model.addAttribute("roomList", roomList);
        System.out.println(roomList);

        return "Reservation/reservationSelectRoomView";
    }

    @PostMapping("/selectRoom")
    public String reservationSelectRoom(HttpSession session, Model model, RoomDTO roomDTO) {
        System.out.println("PostMapping(reservationSelectRoom) start");
        System.out.println(roomDTO);
        CheckDTO finalDTO = (CheckDTO) session.getAttribute("checkDTO");
        System.out.println("finalDTO : " + finalDTO);
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "str";
        }

        UserDTO logInUser = userService.findById(userId);
        System.out.println("logInUser :" + logInUser);

        Reservation reservation = new Reservation();

        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("저장되는 예약 날짜" + dateFormat);

        reservation.setUserId(logInUser.getId());
        reservation.setCheckIn(finalDTO.getCheckIn());
        System.out.println("저장되는 체크인" + reservation.getCheckIn());
        reservation.setCheckOut(finalDTO.getCheckOut());
        System.out.println("저장되는 체크아웃" + reservation.getCheckOut());
        reservation.setRoomId(roomDTO.getRoomId());
        reservation.setReservationDate(dateFormat.format(today));
        reservation.setFinalPrice((int) ((roomService.findRoomById(roomDTO.getRoomId()).get().getRoomPrice()) *
                0.9));
        System.out.println("저장되는 최종 가격" + reservation.getFinalPrice());
        System.out.println("saveReservation -> " + reservation);
        reservationService.reservationJoin(reservation);
        return "redirect:/";
    }

}
