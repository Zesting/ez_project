package Ezen.project.repository;

// import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.Room;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    /** 룸 객체 저장 */
    // Room save(Room room);

    /** 모든 룸 출력(List <Room>) */
    // List<Room> findByAll();

    /** 룸 고유 번호에 따른 룸 출력(Optional <Room>) */
    // Optional<Room> findById(Long roomId);

    /** 룸 이름에 따른 룸 출력(Optional <Room>) */
    // Optional<Room> findByName(String roomName);

    /** 룸 정보 수정(Optional <Room>) */
    // Optional<Room> update(Room room);

    /** 룸 정보 삭제(매개변수 roomId) */
    // void delete(Long roomId);
    List<Room> findByRoomType(String roomType);
}
