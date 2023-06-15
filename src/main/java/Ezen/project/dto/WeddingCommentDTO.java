package Ezen.project.DTO;

import java.time.LocalDateTime;

import Ezen.project.domain.WeddingCommentEntity;
import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class WeddingCommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long weddingId;
    private LocalDateTime commentCreatedTime;

    public static WeddingCommentDTO toWeddingCommentDTO(WeddingCommentEntity weddingCommentEntity, Long weddingId) {
        WeddingCommentDTO weddingCommentDTO = new WeddingCommentDTO();
        weddingCommentDTO.setId(weddingCommentEntity.getId());
        weddingCommentDTO.setCommentWriter(weddingCommentEntity.getCommentWriter());
        weddingCommentDTO.setCommentContents(weddingCommentEntity.getCommentContents());
        weddingCommentDTO.setCommentCreatedTime(weddingCommentEntity.getWeddingWriteDate());
        // weddingCommentDTO.setWeddingId(weddingCommentEntity.getId()); // Service 메서드에 @Transactional
        weddingCommentDTO.setWeddingId(weddingId);
        return weddingCommentDTO;
    }
    
}
