package Ezen.project.domain;

import Ezen.project.DTO.NoticeDTO;
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
@Table(name = "notice")
public class NoticeDomain extends BaseNoticeDomain {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long noticeId; //Entity 공지글 id

    @Column(name = "userId")
    private Long userId; //Entity 공지글 작성자

    @Column(name = "title")
    private String noticeTitle; //Entity 공지글 제목

    @Column(name = "content")
    private String noticeContent; //Entity 공지글 내용

    @Column(name = "file")
    private byte[] noticeFile;//Entity 공지글 첨부파일

    //DTO에서 Entity로 데이터 넣기
    public static NoticeDomain toSaveEntity(NoticeDTO noticeDTO){
        NoticeDomain noticeDomain = new NoticeDomain();
        noticeDomain.setNoticeId(noticeDTO.getId());
        noticeDomain.setUserId(noticeDTO.getUserId());
        noticeDomain.setNoticeTitle(noticeDTO.getTitle());
        noticeDomain.setNoticeContent(noticeDTO.getContent());
        noticeDomain.setNoticeWriteDate(noticeDTO.getWriteDate());
        noticeDomain.setNoticeFile(noticeDTO.getNoticeFile());
        return noticeDomain;
    }

}
