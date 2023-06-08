package Ezen.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.NoticeDomain;

public interface NoticeRepository extends JpaRepository<NoticeDomain, Long>{
    
    // NoticeDomain noticeSave(NoticeDomain notice);

    // Optional<NoticeDomain> findById(Long noticeId);

    // Optional<NoticeDomain> update(NoticeDomain notice);

    // Long Delete(Long noticeId);
    
}
