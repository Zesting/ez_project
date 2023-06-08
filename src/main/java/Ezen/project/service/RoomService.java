package Ezen.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import Ezen.project.domain.Room;
import Ezen.project.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    // 룸 추가 (비지니스 로직)
    @Transactional
    public Long roomJoin(Room room) {
        roomRepository.save(room);
        return room.getRoomId();
    }

    // 룸 전체 조회(비지니스 로직)
    @Transactional(readOnly = true)
    public List<Room> findAllRoom() {
        System.out.println("룸 전체 조회 비지니스 로직 출력");
        return roomRepository.findByAll();
    }

    // 룸 고유 번호로 룸 조회(비지니스 로직)
    @Transactional(readOnly = true)
    public Optional<Room> findRoomById(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            System.out.println("조회된 room : " + room.get());
            return room;
        } else {
            System.out.println("입력된 고유 번호에 대한 룸이 존재하지 않습니다.(룸 고유 번호로 룸 조회 실패)");
            return Optional.empty();
        }
    }

    // 룸 이름으로 룸 조회(비지니스 로직)
    @Transactional(readOnly = true)
    public Optional<Room> findRoomByName(String RoomName) {
        Optional<Room> room = roomRepository.findByName(RoomName);
        if (room.isPresent()) {
            System.out.println("조회된 room : " + room.get());
            return room;
        } else {
            System.out.println("입력된 이름에 대한 룸이 존재하지 않습니다.(룸 조회 실패)");
            return Optional.empty();
        }
    }

    @Transactional
    public void dropRoom(Long roomId) {
        System.out.println("삭제된 룸 : " + roomRepository.findById(roomId).get());
        roomRepository.delete(verificationRoom(roomId));
    }

    public Long verificationRoom(Long roomId) {
        Optional<Room> dropRoom = roomRepository.findById(roomId);
        if (dropRoom.isPresent()) {
            return dropRoom.get().getRoomId();
        } else {
            return null;
        }
    }

}
