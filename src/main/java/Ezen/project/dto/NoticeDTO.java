package Ezen.project.DTO;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Ezen.project.domain.NoticeDomain;
import Ezen.project.domain.NoticeFileDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class NoticeDTO {
    private Long id;        //DTO 공지글 id
    private Long userId;        //DTO 공지글 작성자
    private String title;       //DTO 공지글 제목
    private String content;     //DTO 공지글 내용
    private LocalDateTime writeDate;        //DTO 공지글 작성일
    
    private MultipartFile noticeFile; //DTO 공지글 첨부파일
    // 위 파일 필드는 save.html -> Controller 파일 담는 용도
    private String originalFileName; //원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private Integer fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)
    

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

        if(noticeDomain.getFileAttached() == null){
            noticeDTO.setFileAttached(0); //0
        } else {
            noticeDTO.setFileAttached(noticeDomain.getFileAttached().intValue()); //1
            // 파일 이름을 가져가야 함
            // originalFileName, storedFileName : noticeFile(noticeFileEntity)
            // join
            // select * from notice n, noticeFile nf where n.id = nf.id and where n.id = ?
            
            // noticeDTO.setOriginalFileName(noticeDomain.getNoticeFileDomainList().get(0).getOriginalFileName());
            // noticeDTO.setStoredFileName(noticeDomain.getNoticeFileDomainList().get(0).getStoredFileName());

            List<NoticeFileDomain> noticeFileDomainList = noticeDomain.getNoticeFileDomainList();
        if (noticeFileDomainList != null && !noticeFileDomainList.isEmpty()) {
            noticeDTO.setOriginalFileName(noticeFileDomainList.get(0).getOriginalFileName());
            noticeDTO.setStoredFileName(noticeFileDomainList.get(0).getStoredFileName());
        }
        }
        return noticeDTO;
    }
}
