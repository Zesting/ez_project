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
    public Long roomJoin(Room room) {
        roomRepository.save(room);
        return room.getRoomId();
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
        Room roomtemp = room.get();
        roomtemp.setRoomName("이상한이름");
        if (room.isPresent()) {
            System.out.println("조회된 room : " + room.get());
            return room;
        } else {
            System.out.println("입력된 고유 번호에 대한 룸이 존재하지 않습니다.(룸 고유 번호로 룸 조회 실패)");
            return Optional.empty();
        }
    }

    // 룸 이름으로 룸 조회 기능[관리자 파트] (비지니스 로직)
    @Transactional(readOnly = true)
    public Optional<Room> findRoomByName(String roomName) {
        Optional<Room> room = roomRepository.findAll().stream().filter(r -> r.getRoomName().equals(roomName)).findAny();
        if (room.isPresent()) {
            System.out.println("조회된 room : " + room.get());
            return room;
        } else {
            System.out.println("입력된 이름에 대한 룸이 존재하지 않습니다.(룸 조회 실패)");
            return Optional.empty();
        }
    }

    // 룸 객체 정보 변경 기능[관리자 파트] (비지니스 로직)
    @Transactional
    public Optional<Room> modifyRoom(Room room) {
        System.out.println("룸 객제 정보 변경 시작");
        if (!(room.getRoomId() == null)) {
            Optional<Room> roomOptional = roomRepository.findById(room.getRoomId());
            if (roomOptional.isPresent()) {
                // Room updateRoom = roomOptional.get();
                // updateRoom.setRoomName(room.getRoomName());
                // updateRoom.setRoomPrice(room.getRoomPrice());
                // updateRoom.setRoomType(room.getRoomType());
                // updateRoom.setRoomDetailInfo(room.getRoomDetailInfo());
                // updateRoom.setRoomImagePath(room.getRoomImagePath());
                System.out.println("룸 서비스 if문 시작");
                roomRepository.save(room);
                return Optional.of(room);
            } else {
                System.out.println("룸 서비스 로직 실패1");
                return Optional.empty();
            }
        } else {
            System.out.println("룸 서비스 로직 실패2");
            return Optional.empty();
        }
    }

    public Room roomConverter(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setRoomName(roomDTO.getRoomName());
        room.setRoomPrice(roomDTO.getRoomPrice());
        room.setRoomType(roomDTO.getRoomType());
        room.setRoomDetailInfo(roomDTO.getRoomDetailInfo());
        room.setRoomImagePath(roomDTO.getRoomImagePath());
        return room;
    }

    // 룸 삭제 기능 기능[관리자 파트] (비지니스 로직)
    @Transactional
    public void dropRoom(Long roomId) {
        System.out.println("삭제된 룸 : " + roomRepository.findById(roomId).get());
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
