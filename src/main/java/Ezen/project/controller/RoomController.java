package Ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Ezen.project.service.RoomService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/Rooms")
public class RoomController {

    private final RoomService roomService;

    // 룸 리스트 출력 메서드
    // @GetMapping(value = "")
    // public String roomList(Model model) {
    // model.addAttribute("roomList", roomService.findAllRoom());
    // return "Room/roomList";
    // }

    // 룸 생성 뷰 출력 메서드
    @GetMapping(value = "/add")
    public String roomJoinView() {
        return "Room/roomCreate";
    }

    // 룸 수정 뷰 컨트롤러
    // @GetMapping("/modify/{id}")
    // public String roomModifyView(@PathVariable("id") Long id, Model model) {
    // model.addAttribute("roomData", roomService.findRoomById(id).get());
    // return "Room/roomModify";
    // }

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
