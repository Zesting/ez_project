package Ezen.project.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseWeddingDomain {
    
    @CreationTimestamp
    @Column(name = "weddingWriteDate")
    private LocalDateTime weddingWriteDate; // wedding 문의글 작성일
}
