package Ezen.project.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notice")
public class NoticeDomain {
    
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

    @Column(name = "WriteDate")
    private Date noticeWriteDate;

    // @Column(name = "file")
    // private byte[] noticeFile;


}
