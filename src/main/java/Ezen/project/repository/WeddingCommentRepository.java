package Ezen.project.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.WeddingCommentEntity;
import Ezen.project.domain.WeddingDomain;

public interface WeddingCommentRepository extends JpaRepository<WeddingCommentEntity, Long>{
    List<WeddingCommentEntity> findAllByWeddingDomainOrderByIdDesc(WeddingDomain weddingDomain);
}
