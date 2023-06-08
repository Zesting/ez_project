package Ezen.project.DTO;

import java.time.LocalDateTime;

import Ezen.project.domain.NoticeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime writeDate;
    private byte[] noticeFile;

    public static NoticeDTO toNoticeDTO(NoticeDomain noticeDomain){
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(noticeDomain.getNoticeId());
        noticeDTO.setUserId(noticeDomain.getUserId());
        noticeDTO.setTitle(noticeDomain.getNoticeTitle());
        noticeDTO.setContent(noticeDomain.getNoticeContent());
        noticeDTO.setWriteDate(noticeDomain.getNoticeWriteDate());
        noticeDTO.setNoticeFile(noticeDomain.getNoticeFile());
        return noticeDTO;
    }
}
