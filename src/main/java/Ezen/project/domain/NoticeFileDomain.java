package Ezen.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "noticeFile")
public class NoticeFileDomain extends BaseNoticeDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Column(name = "storedFileName")
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeId")
    private NoticeDomain noticeDomain;

    public static NoticeFileDomain toNoticeFileEntity(NoticeDomain noticeDomain, String originalFileName, String storedFileName){
        NoticeFileDomain noticeFileDomain = new NoticeFileDomain();
        noticeFileDomain.setOriginalFileName(originalFileName);
        noticeFileDomain.setStoredFileName(storedFileName);
        noticeFileDomain.setNoticeDomain(noticeDomain);
        return noticeFileDomain;
    }
    
}
