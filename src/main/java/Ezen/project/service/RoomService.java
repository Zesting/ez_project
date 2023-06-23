package Ezen.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Ezen.project.DTO.RoomDTO;
import Ezen.project.domain.Room;
import Ezen.project.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    // 룸 추가 기능[관리자 파트] (비지니스 로직)
    @Transactional // AOP
    public Optional<Room> roomJoin(RoomDTO roomDTO) {
        Room room = roomConverter(roomDTO);
        roomRepository.save(room);
        return Optional.of(room);
    }

    // 룸 전체 조회 기능[관리자 파트] (비지니스 로직)
    @Transactional(readOnly = true)
    public List<Room> findAllRoom() {
        System.out.println("룸 전체 조회 비지니스 로직 출력");
        return roomRepository.findAll();
    }

    // 룸 고유 번호로 룸 조회 기능[관리자 파트] (비지니스 로직)
    @Transactional(readOnly = true)
    public Optional<Room> findRoomById(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            return room;
        } else {
            return Optional.empty();
        }
    }

    // 룸 이름으로 룸 조회 기능[관리자 파트] (비지니스 로직)
    @Transactional(readOnly = true)
    public Optional<Room> findRoomByName(String roomName) {
        Optional<Room> room = roomRepository.findAll().stream().filter(r -> r.getRoomName().equals(roomName)).findAny();
        if (room.isPresent()) {
            return room;
        } else {
            return Optional.empty();
        }
    }

    // 룸 객체 정보 변경 기능[관리자 파트] (비지니스 로직)
    @Transactional
    public Optional<Room> modifyRoom(RoomDTO roomDTO) {
        if (!(roomDTO.getRoomId() == null)) {
            Optional<Room> roomOptionalDTO = roomRepository.findById(roomDTO.getRoomId());
            if (roomOptionalDTO.isPresent()) {
                Room room = roomConverter(roomDTO);
                roomRepository.save(room);
                return Optional.of(room);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public Room roomConverter(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setRoomName(roomDTO.getRoomName());
        room.setRoomPrice(roomDTO.getRoomPrice());
        room.setRoomType(roomDTO.getRoomType());
        return room;
    }

    // 룸 삭제 기능 기능[관리자 파트] (비지니스 로직)
    @Transactional
    public void dropRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    // 룸 존재 여부 검증 기능
    public Long verificationRoom(Long roomId) {
        Optional<Room> dropRoom = roomRepository.findById(roomId);
        if (dropRoom.isPresent()) {
            return dropRoom.get().getRoomId();
        } else {
            return null;
        }
    }

}
