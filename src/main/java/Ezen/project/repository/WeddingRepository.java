package Ezen.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.WeddingDomain;

//Jpa 기능 불러오는 인터페이스
public interface WeddingRepository extends JpaRepository<WeddingDomain, Long>{
    
}
