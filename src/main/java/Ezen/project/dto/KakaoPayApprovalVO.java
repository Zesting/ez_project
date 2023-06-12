package Ezen.project.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class KakaoPayApprovalVO {

  // response
  //amount와 card_info는 JSON Object로 전송받기 때문에 객체를 따로 만들어준다.

  //요청 고유 번호/결제 고유 번호/가맹점 코드/결제상태(정기,단건)
  private String aid, tid, cid, sid;
  //가맹점 주문번호/가맹점 회원 id/ 결제 수단, CARD 또는 MONEY 중 하나
  private String partner_order_id, partner_user_id, payment_method_type;
  //결제 금액 정보
  private AmountVO amount;  
  //결제 상세 정보, 결제수단이 카드일 경우만 포함
  private CardVO card_info; 
  //상품 이름/상품 코드/결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용
  private String item_name, item_code, payload;
  //상품 수량
  private Integer quantity;
  //결제 준비 요청 시각/결제 승인 시각
  private Date created_at, approved_at;
}
