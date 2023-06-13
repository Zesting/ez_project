package Ezen.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Ezen.project.DTO.RoomDTO;
import Ezen.project.domain.Room;
import Ezen.project.service.RoomService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/Room")
public class RoomController {

    private final RoomService roomService;

    // 룸 리스트 출력 메서드
    @GetMapping("/list")
    public String roomList(Model model) {
        List<Room> roomList = roomService.findAllRoom();
        model.addAttribute("roomList", roomList);
        System.out.println("roomList : " + roomList);
        return "Room/roomList";
    }

    // 룸 생성 뷰 출력 메서드
    @GetMapping("/create")
    public String roomJoinView() {
        return "Room/roomCreate";
    }

    // 룸 생성 데이터 저장 로직
    @PostMapping("/create")
    public String roomJoin(RoomDTO roomDTO) {
        // 관리자 권한을 가진 사용자만 가능하도록 리펙토링할 듯
        // 해당 구성은 다음주에 협업 단계;
        Room room = new Room();

        room.setRoomName(roomDTO.getRoomName());
        room.setRoomPrice(roomDTO.getRoomPrice());
        room.setRoomType(roomDTO.getRoomType());
        room.setRoomDetailInfo(roomDTO.getRoomDetailInfo());
        room.setRoomImagePath(roomDTO.getRoomImagePath());
        roomService.roomJoin(room);
        System.out.println("저장된 room : " + room);

        return "redirect:/";
    }

    // 룸 수정 뷰 컨트롤러
    @GetMapping("/modify/{id}")
    public String roomModifyView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roomData", roomService.findRoomById(id).get());
        return "Room/roomModify";
    }

    // 룸 객체 데이터 메서드
    @PostMapping("/modify/{id}")
    public String roomModify(@PathVariable Long id, RoomDTO roomDTO) {
        System.out.println("roomDTO : " + roomDTO);
        Optional<Room> coverOriginalRoom = roomService.findRoomById(id);
        if (coverOriginalRoom.isPresent()) {
            Room originalRoom = coverOriginalRoom.get();
            System.out.println("originalRoom : " + originalRoom);
            Room updateRoom = roomService.roomConverter(roomDTO);
            System.out.println("updateRoom1" + updateRoom);
            // updateRoom.setRoomId(originalRoom.getRoomId());
            // updateRoom.setRoomName(originalRoom.getRoomName());
            // updateRoom.setRoomPrice(originalRoom.getRoomPrice());
            // updateRoom.setRoomType(originalRoom.getRoomType());
            // updateRoom.setRoomDetailInfo(originalRoom.getRoomDetailInfo());
            // updateRoom.setRoomImagePath(originalRoom.getRoomImagePath());
            roomService.modifyRoom(updateRoom);
        }
        return "redirect:list";
    }

    // 룸 삭제 뷰 출력 메서드(해당 구성은 뷰 리스트 내에 버튼으로 수정될 예정)
    @GetMapping("/drop/{id}")
    public String roomDropView(@PathVariable Long id) {
        roomService.verificationRoom(id);
        roomService.dropRoom(id);
        return "redirect: list";
    }

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
