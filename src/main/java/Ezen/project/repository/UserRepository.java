package Ezen.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Ezen.project.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    
    // : 는 쿼리의 매개변수를 바인딩하기 위한 플레이스홀더를 나타내는 기호다.
    @Query("SELECT userId From User u WHERE u.userId= :mid")
    String checkUserId(@Param("mid") String mid);
}
