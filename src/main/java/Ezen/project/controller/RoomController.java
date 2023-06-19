package Ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Ezen.project.service.RoomService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    // 룸 리스트 출력 메서드
    @RequestMapping(value = "/Rooms", method = RequestMethod.GET)
    public String roomList(Model model) {
        model.addAttribute("roomList", roomService.findAllRoom());
        return "Room/roomList";
    }

    // 룸 생성 뷰 출력 메서드
    @RequestMapping(value = "/Rooms/add", method = RequestMethod.GET)
    public String roomJoinView() {
        return "Room/roomCreate";
    }

    // 룸 수정 뷰 컨트롤러
    @RequestMapping(value = "/Rooms/modify/{id}", method = RequestMethod.GET)
    public String roomModifyView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("data", roomService.findRoomById(id).get());
        return "Room/roomModify";
    }

    @RequestMapping(value = "/Rooms/room_Base", method = RequestMethod.GET)
    public String roomBaseView() {
        return "Room/room_Base";
    }

    @RequestMapping(value = "/Rooms/room_MercuryView", method = RequestMethod.GET)
    public String roomMercuryView() {
        return "Room/room_Mercury";
    }

    @RequestMapping(value = "/Rooms/room_MarsView", method = RequestMethod.GET)
    public String roomMarsView() {
        return "Room/room_Mars";
    }

    @RequestMapping(value = "/Rooms/room_EarthView", method = RequestMethod.GET)
    public String roomEarthView() {
        return "Room/room_Earth";
    }

    @RequestMapping(value = "/Rooms/room_JupiterView", method = RequestMethod.GET)
    public String roomJupiterView() {
        return "Room/room_Jupiter";
    }

    // 룸 수정
    // @PostMapping("/modify/{id}")
    // public String roomModify(@PathVariable("id") Long id, RoomDTO roomDTO) {
    // System.out.println("roomDTO : " + roomDTO);
    // Optional<Room> coverOriginalRoom = roomService.findRoomById(id);
    // if (coverOriginalRoom.isPresent()) {
    // roomService.modifyRoom(roomDTO);
    // }
    // return "redirect:/Rooms";
    // }

    // 룸 삭제 뷰 출력 메서드(해당 구성은 뷰 리스트 내에 버튼으로 수정될 예정)
    // @GetMapping("/drop/{id}")
    // public String roomDropView(@PathVariable Long id) {
    // roomService.verificationRoom(id);
    // roomService.dropRoom(id);
    // return "redirect:/Rooms";
    // }

    // 룸 삭제 로직 메서드
    /*
     * @PostMapping("/dropRoom")
     * public String roomDrop(Long roomId) {
     * roomService.verificationRoom(roomId);
     * roomService.dropRoom(roomId);
     * return "redirect:/";
     * }
     */

}
