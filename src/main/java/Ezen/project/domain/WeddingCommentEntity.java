package Ezen.project.domain;

import Ezen.project.DTO.WeddingCommentDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "weddingComment")
public class WeddingCommentEntity extends BaseWeddingDomain{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "commentWriter")
    private String commentWriter;

    @Column(name = "commentContents")
    private String commentContents;

    /* Wedding:Comment = 1:N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weddingId")
    private WeddingDomain weddingDomain;

    public static WeddingCommentEntity toSaveEntity(WeddingCommentDTO weddingCommentDTO, WeddingDomain weddingDomain) {
        WeddingCommentEntity weddingCommentEntity = new WeddingCommentEntity();
        weddingCommentEntity.setCommentWriter(weddingCommentDTO.getCommentWriter());
        weddingCommentEntity.setCommentContents(weddingCommentDTO.getCommentContents());
        weddingCommentEntity.setWeddingDomain(weddingDomain);
        return weddingCommentEntity;
    }


}
