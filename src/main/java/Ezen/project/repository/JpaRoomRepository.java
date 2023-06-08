package Ezen.project.repository;

// import java.util.List;
// import java.util.Optional;

// import Ezen.project.domain.Room;
// import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaRoomRepository {

    // private final EntityManager em;

    // @Override /** 룸 객체 저장 */
    // public Room save(Room room) {
    // em.persist(room);
    // return room;
    //// }

    // @Override /** 모든 룸 출력(List <Room>) */
    // public List<Room> findByAll() {
    // System.out.println("room Jpa Repository 수행");
    // return em.createQuery("select r from Room r", Room.class).getResultList();
    // }

    /// ** 룸 고유 번호에 따른 룸 출력(Optional <Room>) */
    // @Override
    // public Optional<Room> findById(Long roomId) {
    // // em.create ~ getSingleResult()까지 수행하면 Room 객체 반환
    // // 반환된 Room 객체 Optional.ofNullable()사용해서 null 여부 확인하고 리턴.
    // return Optional.ofNullable(em.createQuery("Select r from Room r where
    /// r.id=:id", Room.class)
    // .setParameter("id", roomId).getSingleResult());
    // }

    /// ** 룸 이름에 따른 룸 출력(Optional <Room>) */
    // @Override
    // public Optional<Room> findByName(String roomName) {
    // return Optional.ofNullable(em.createQuery("Select r from Room r where
    /// r.name=:name", Room.class)
    // .setParameter("name", roomName).getSingleResult());
    // }

    /// ** 입력된 룸 폼에 따라 룸 업데이트(Optional <Room>) */
    // @Override
    // public Optional<Room> update(Room room) {
    // Room existingRoom = em.find(Room.class, room.getRoomId());
    // if (existingRoom != null) {
    // existingRoom.setRoomId(room.getRoomId());
    // existingRoom.setRoomName(room.getRoomName());
    // existingRoom.setRoomPrice(room.getRoomPrice());
    // existingRoom.setRoomType(room.getRoomType());
    // existingRoom.setRoomDetailInfo(room.getRoomDetailInfo());
    // existingRoom.setRoomImagePath(room.getRoomImagePath());
    // return Optional.of(existingRoom);
    // }
    // return Optional.empty();
    // }
    //
    /// ** 룸 정보 삭제(매개변수 roomId) */
    // @Override
    // public void delete(Long roomId) {
    // em.createQuery("Delete from Room r where r.id = :id").setParameter("id",
    // roomId).executeUpdate();
    // em.clear();
    // }
    //
}
