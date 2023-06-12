package Ezen.project.DTO;

import lombok.Data;

@Data
public class KakaoPayCancelVO {
  private String cid; // 가맹점 코드, 필수O
  private String cid_secret; // 가맹점 코드 인증키, 필수x
  private String tid; // 결제 고유번호	O
  private Integer cancel_amount;  //취소 금액	O
  private Integer cancel_tax_free_amount; //취소 비과세 금액	O

}
