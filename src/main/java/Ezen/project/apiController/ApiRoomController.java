package Ezen.project.apiController;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Ezen.project.DTO.RoomDTO;
import Ezen.project.domain.Room;
import Ezen.project.service.RoomService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiRoomController {

    private final RoomService roomService;

    // @RequestMapping(value = "/Rooms", method = RequestMethod.GET)
    // public ResponseEntity<?> roomList() { // ? = room
    // return ResponseEntity.ok(roomService.findAllRoom());
    // }

    @RequestMapping(value = "/Rooms/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody @Validated RoomDTO roomDTO) { // ? = room
        Optional<Room> result = roomService.roomJoin(roomDTO);

        if (result.isPresent()) {
            Room createRoom = result.get();
            return ResponseEntity.ok(createRoom);
        }
        return ResponseEntity.notFound().build();
    }

    // @RequestMapping(value = "/Rooms/modify/{id}", method = RequestMethod.GET)
    // public ResponseEntity<?> findOneByRoom(@PathVariable("id") Long id) {
    // return ResponseEntity.ok(roomService.findRoomById(id));
    // }

    @RequestMapping(value = "/Rooms/modify/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> roomModify(@RequestBody @Validated RoomDTO roomDTO, @PathVariable("id") Long id) {
        Optional<?> result = roomService.modifyRoom(roomDTO);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "Rooms/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> roomDelete(@PathVariable Long id) {
        roomService.verificationRoom(id);
        roomService.dropRoom(id);
        return ResponseEntity.ok(id);
    }

}
