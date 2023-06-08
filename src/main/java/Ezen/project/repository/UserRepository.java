package Ezen.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.User;


public interface UserRepository extends JpaRepository<User,Long>{
    
}
