package Ezen.project.apiController;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<?> add(@RequestBody RoomDTO roomDTO) { // ? = room
        Optional<?> result = roomService.roomJoin(roomDTO);
        return ResponseEntity.ok(result.get());
    }

    @GetMapping(value = "")
    public ResponseEntity<?> roomList() { // ? = room
        return ResponseEntity.ok(roomService.findAllRoom());
    }

    @GetMapping(value = "/modify/{id}")
    public ResponseEntity<?> findOneByRoom(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roomService.findRoomById(id));
    }

    @PutMapping(value = "/modify/{id}")
    public ResponseEntity<?> roomModify(@RequestBody RoomDTO roomDTO, @PathVariable("id") Long id) {
        Optional<?> result = roomService.modifyRoom(roomDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> roomDelete(@PathVariable Long id) {
        roomService.verificationRoom(id);
        roomService.dropRoom(id);
        return ResponseEntity.ok().build();
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
