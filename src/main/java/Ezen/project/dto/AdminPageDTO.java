package Ezen.project.DTO;

import Ezen.project.domain.AdminPage;
import lombok.Data;

//DTO(Data Transfer Object), VO, Bean, Entity
@Data
public class AdminPageDTO {
  private Long adminId; //관리자고유번호(PK)
  private Long userId;  //회원고유번호(FK)
  private Long noticeId;  //공지사항고유번호(FK)
  private Long weddingId; //문의사항고유번호(FK)
  private Long roomId;  //방고유번호(FK)
  private Long paymentId; //결제고유번호(FK)
  private Long reservationId; //예약고유번호(FK)

  //Entity -> DTO (DTO class에서 변환)
  public static AdminPageDTO toAdminPageDTO(AdminPage adminPage){
    AdminPageDTO adminPageDTO = new AdminPageDTO();
    adminPageDTO.setAdminId(adminPage.getAdminId());
    adminPageDTO.setUserId(adminPage.getUserId());
    adminPageDTO.setNoticeId(adminPage.getNoticeId());
    adminPageDTO.setWeddingId(adminPage.getWeddingId());
    adminPageDTO.setRoomId(adminPage.getRoomId());
    adminPageDTO.setPaymentId(adminPage.getPaymentId());
    adminPageDTO.setReservationId(adminPage.getReservationId());
    return adminPageDTO;
  }
}
