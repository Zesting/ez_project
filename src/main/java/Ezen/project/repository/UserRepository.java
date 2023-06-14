package Ezen.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Ezen.project.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    
    @Query("SELECT u From User u WHERE u.userId=mid")
    String checkUserId(@Param("mid") String mid);
}
