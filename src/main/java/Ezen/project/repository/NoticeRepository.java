package Ezen.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.NoticeDomain;

public interface NoticeRepository extends JpaRepository<NoticeDomain, Long>{

    
}
