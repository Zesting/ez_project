package Ezen.project.domain;

import java.util.ArrayList;
import java.util.List;

import Ezen.project.DTO.NoticeDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    private Integer fileAttached; // 1 or 0

    @OneToMany(mappedBy = "noticeDomain", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NoticeFileDomain> noticeFileDomainList = new ArrayList<>();

    //DTO에서 Entity로 데이터 넣기
    public static NoticeDomain toSaveEntity(NoticeDTO noticeDTO){
        NoticeDomain noticeDomain = new NoticeDomain();
        noticeDomain.setNoticeId(noticeDTO.getId());
        noticeDomain.setUserId(noticeDTO.getUserId());
        noticeDomain.setNoticeTitle(noticeDTO.getTitle());
        noticeDomain.setNoticeContent(noticeDTO.getContent());
        noticeDomain.setNoticeWriteDate(noticeDTO.getWriteDate());
        // noticeDomain.setFileAttached(null); // 파일 없음
        noticeDomain.setFileAttached(noticeDTO.getFileAttached() != null ? noticeDTO.getFileAttached() : 0); // 파일 없으면 0, 파일 있으면 값 사용
        return noticeDomain;
    }

    public static NoticeDomain toUpdateEntity(NoticeDTO noticeDTO){
        NoticeDomain noticeDomain = new NoticeDomain();
        noticeDomain.setNoticeId(noticeDTO.getId());
        noticeDomain.setUserId(noticeDTO.getUserId());
        noticeDomain.setNoticeTitle(noticeDTO.getTitle());
        noticeDomain.setNoticeContent(noticeDTO.getContent());
        noticeDomain.setNoticeWriteDate(noticeDTO.getWriteDate());
        noticeDomain.setFileAttached(noticeDTO.getFileAttached() != null ? noticeDTO.getFileAttached() : 0); // 파일 없으면 0, 파일 있으면 값 사용
        return noticeDomain;
    }

    public static NoticeDomain toSaveFileEntity(NoticeDTO noticeDTO) {
        NoticeDomain noticeDomain = new NoticeDomain();
        noticeDomain.setNoticeId(noticeDTO.getId());
        noticeDomain.setUserId(noticeDTO.getUserId());
        noticeDomain.setNoticeTitle(noticeDTO.getTitle());
        noticeDomain.setNoticeContent(noticeDTO.getContent());
        noticeDomain.setNoticeWriteDate(noticeDTO.getWriteDate());
        // noticeDomain.setFileAttached(1); //파일 있음
        noticeDomain.setFileAttached(noticeDTO.getFileAttached() != null ? noticeDTO.getFileAttached() : 1); // 파일 있으면 1, 파일 없으면 값 사용
        return noticeDomain;
    }

}
