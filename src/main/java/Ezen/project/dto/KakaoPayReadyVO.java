package Ezen.project.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class KakaoPayReadyVO {//결제 준비 > 응답
  
  //response 데이터를 받을 곳이다.
  private String tid; //결제 고유번호. 20자
  private String next_redirect_pc_url;  //요청한 클라이언트가 pc일경우 리다이렉트, 카카오톡으로 TMS보내기 위한 사용자입력화면으로 리다이렉트
  private Date created_at;  //결제 준비 요청 시간

}
