package Ezen.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.DTO.RoomDTO;
import Ezen.project.domain.Room;
import Ezen.project.service.RoomService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RoomController {

    private final RoomService roomService;

    // 룸 리스트 출력 메서드
    @GetMapping("/room")
    public String roomList(Model model) {
        List<Room> roomList = roomService.findAllRoom();
        model.addAttribute("roomList", roomList);
        System.out.println("roomList : " + roomList);
        return "Room/roomList";
    }

    // 룸 생성 뷰 출력 메서드
    @GetMapping("/createRoom")
    public String roomJoinView() {
        return "Room/roomCreate";
    }

    // 룸 생성 데이터 저장 메서드
    @PostMapping("/createRoom")
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

    @GetMapping("/modifyRoom")
    public String roomModifyView(Model model) {
        model.addAttribute("testRoom", roomService.findRoomById(1l).get());
        return "Room/roomModify";
    }

    @PostMapping("/modifyRoom")
    public String roomModify(RoomDTO roomDTO) {
        System.out.println("roomDTO : " + roomDTO);
        Optional<Room> coverOriginalRoom = roomService.findRoomById(roomDTO.getRoomId());
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
            System.out.println("updateRoom2" + updateRoom);
            roomService.modifyRoom(updateRoom);
        }
        return "redirect:/";
    }

    // 룸 삭제 뷰 출력 메서드(해당 구성은 뷰 리스트 내에 버튼으로 수정될 예정)
    @GetMapping("/dropRoom")
    public String roomDropView() {
        return "Room/roomDrop";
    }

    @PostMapping("/dropRoom")
    public String roomDrop(Long roomId) {
        roomService.verificationRoom(roomId);
        roomService.dropRoom(roomId);
        return "redirect:/";
    }

}
