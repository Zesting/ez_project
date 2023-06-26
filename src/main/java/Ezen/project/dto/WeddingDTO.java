package Ezen.project.DTO;

import java.sql.Date;
import java.time.LocalDateTime;

import Ezen.project.domain.WeddingDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeddingDTO {
    private Long weddingId; // DTO 문의글 id -
    private Long userId; // DTO 문의글 작성자 -
    private String userName;
    private String weddingTitle; // DTO 문의글 제목 -
    private String weddingContent; // DTO 문의글 내용
    private LocalDateTime weddingWriteDate; // DTO 문의글 작성일 -
    private Date weddingDueDate; // DTO 문의글 예정일
    private byte[] weddingFile; // DTO 문의글 첨부파일

    public WeddingDTO(Long weddingId, Long userId, String weddingTitle, LocalDateTime weddingWriteDate) {
        this.weddingId = weddingId;
        this.userId = userId;
        this.weddingTitle = weddingTitle;
        this.weddingWriteDate = weddingWriteDate;
    }

    public static WeddingDTO toWeddingDTO(WeddingDomain weddingDomain) {
        WeddingDTO weddingDTO = new WeddingDTO();
        weddingDTO.setWeddingId(weddingDomain.getWeddingId());
        weddingDTO.setUserId(weddingDomain.getUserId());
        weddingDTO.setWeddingTitle(weddingDomain.getWeddingTitle());
        weddingDTO.setWeddingContent(weddingDomain.getWeddingContent());
        weddingDTO.setWeddingWriteDate(weddingDomain.getWeddingWriteDate());
        weddingDTO.setWeddingDueDate(weddingDomain.getWeddingDueDate());
        weddingDTO.setWeddingFile(weddingDomain.getWeddingFile());
        return weddingDTO;
    }

}
