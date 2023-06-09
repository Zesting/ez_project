package Ezen.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "adminpage")
public class AdminPage {
  
  @Id //기본키 설정
  @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가
  @Column(name = "id")
  private Long adminId; //관리자고유번호(PK)

  @Column(name = "userId")
  private Long userId;  //회원고유번호(FK)

  @Column(name = "noticeId")
  private Long noticeId;  //공지사항고유번호(FK)

  @Column(name = "weddingId")
  private Long weddingId; //문의사항고유번호(FK)

  @Column(name = "roomId")
  private Long roomId;  //방고유번호(FK)

  @Column(name = "paymentId")
  private Long paymentId; //결제고유번호(FK)

  @Column(name = "reservationId")
  private Long reservationId; //예약고유번호(FK)
}
