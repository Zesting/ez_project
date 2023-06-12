package Ezen.project.apiController;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ezen.project.DTO.RoomDTO;
import Ezen.project.domain.Room;
import Ezen.project.service.RoomService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Rooms")
public class ApiRoomController {

    private final RoomService roomService;

    @PostMapping(value = "/add")
    public ResponseEntity<Room> add(@RequestBody RoomDTO roomDTO) {
        Optional<Room> result = roomService.roomJoin(roomDTO);
        return ResponseEntity.ok(result.get());
    }

    @PutMapping(value = "/modify/{id}")
    public String roomModify(@RequestBody RoomDTO roomDTO, @PathVariable Long id) {
        roomService.modifyRoom(roomDTO);
        return "modify";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String roomDelete(@PathVariable Long id) {
        roomService.verificationRoom(id);
        roomService.dropRoom(id);
        return "delete";
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Room> Join(@RequestBody RoomDTO roomDTO) {
        Optional<Room> result = roomService.roomJoin(roomDTO);

        if (result.isPresent()) {
            Room createRoom = result.get();
            return ResponseEntity.ok(createRoom);
        }
        return ResponseEntity.notFound().build();
    }

}
