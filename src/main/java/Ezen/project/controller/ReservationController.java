package Ezen.project.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Ezen.project.DTO.CheckDTO;
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
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
    private final RoomService roomService;

    @RequestMapping(value = "/Reservations/verificationUser", method = RequestMethod.GET)
    public String verification(HttpSession session, Model model) {
        session.getAttribute("userId");
        return "Reservation/verification";
    }

    // 예약 리스트 리턴
    @RequestMapping(value = "/Reservations", method = RequestMethod.GET)
    public String reservationListView(Model model) {
        List<Reservation> reservationList = reservationService.findAllReservation();
        reservationList.stream().forEach(r -> {
            UserDTO user = userService.findById(r.getUserId());
            Room room = roomService.findRoomById(r.getRoomId()).get();
            model.addAttribute("user", user);
            model.addAttribute("room", room);
        });

        model.addAttribute("reservationList", reservationList);
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

        CheckDTO checkDTO = (CheckDTO) session.getAttribute("checkDTO");
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

    @RequestMapping(value = "/Reservations/myList", method = RequestMethod.GET)
    public String myListView(Model model, HttpSession session) throws NullPointerException {
        Long userId = (Long) session.getAttribute("userId");
        List<Reservation> userReservationList = reservationService.findAllReservationByUserId(userId);
        List<Map<String, Object>> myList = new ArrayList<>();
        userReservationList.stream().forEach(r -> {
            Map<String, Object> innerDate = new LinkedHashMap<>();

            innerDate.put("userName", userService.findById(r.getUserId()).getUserName());
            innerDate.put("reservationDate", r.getReservationDate());
            innerDate.put("roomName", roomService.findRoomById(r.getRoomId()).get().getRoomName());
            innerDate.put("roomType", roomService.findRoomById(r.getRoomId()).get().getRoomType());
            innerDate.put("checkIn", r.getCheckIn());
            innerDate.put("checkOut", r.getCheckOut());
            innerDate.put("finalPrice", r.getFinalPrice());

            myList.add(innerDate);
        });
        model.addAttribute("myList", myList);

        return "Reservation/myReservation";
    }

}
