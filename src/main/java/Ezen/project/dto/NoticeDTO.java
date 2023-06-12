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
    private Long id;        //DTO 공지글 id
    private Long userId;        //DTO 공지글 작성자
    private String title;       //DTO 공지글 제목
    private String content;     //DTO 공지글 내용
    private LocalDateTime writeDate;        //DTO 공지글 작성일
    private byte[] noticeFile;      //DTO 공지글 첨부파일

    public NoticeDTO(Long id, Long userId, String title, LocalDateTime writeDate) {
        this.id =id;
        this.userId = userId;
        this.title = title;
        this.writeDate = writeDate;
    }

    //Entity에서 DTO로 데이터 전송
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
