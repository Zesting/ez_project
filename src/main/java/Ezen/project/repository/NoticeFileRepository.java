package Ezen.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.NoticeFileDomain;

public interface NoticeFileRepository extends JpaRepository<NoticeFileDomain, Long>{
    
}
