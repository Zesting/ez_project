package Ezen.project.domain;

import java.time.LocalDateTime;

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
    private Long noticeId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "title")
    private String noticeTitle;

    @Column(name = "content")
    private String noticeContent;

    @Column(name = "file")
    private byte[] noticeFile;

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
