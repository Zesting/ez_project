package Ezen.project.dto;

import java.time.LocalDateTime;

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
    private LocalDateTime WriteDate;
    private byte[] noticeFile;
}
