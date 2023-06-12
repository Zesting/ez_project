package Ezen.project.domain;

import java.sql.Date;

import Ezen.project.DTO.WeddingDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "wedding")
public class WeddingDomain extends BaseWeddingDomain {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long weddingId; // wedding 문의 id

    @Column(name = "userId")
    private Long userId; // wedding 문의글 작성자

    

    @Column(name = "weddingTitle")
    private String weddingTitle; // wedding 문의글 제목

    @Column(name = "weddingContent")
    private String weddingContent; // wedding 문의글 내용

    @Column(name = "weddingDueDate")
    private Date weddingDueDate; // wedding 문의글 예정일

    @Column(name = "file")
    private byte[] weddingFile; // wedding 문의글 첨부파일

    public static WeddingDomain toSaveEntity(WeddingDTO weddingDTO){
        WeddingDomain weddingDomain = new WeddingDomain();
        weddingDomain.setWeddingId(weddingDTO.getWeddingId());
        weddingDomain.setUserId(weddingDTO.getUserId());
        
        weddingDomain.setWeddingTitle(weddingDTO.getWeddingTitle());
        weddingDomain.setWeddingContent(weddingDTO.getWeddingContent());
        weddingDomain.setWeddingWriteDate(weddingDTO.getWeddingWriteDate());
        weddingDomain.setWeddingDueDate(weddingDTO.getWeddingDueDate());
        weddingDomain.setWeddingFile(weddingDTO.getWeddingFile());
        return weddingDomain;
    }
}
