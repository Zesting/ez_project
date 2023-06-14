package Ezen.project.DTO;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PaymentDTO {
  //paymentCtroller에서 DTO를 셋팅함
  private Long id;
  private Long reservationId;//예약 번호
  private String userId;  //회원 아이디
  private String userName;    //회원 이름
  private String roomName;    //룸 이름
  private int amount;    //가격
  private String userPhoneNumber; //회원 전화번호
  

  // private Long payId;
  // private String userId;
  // private Long reservationId;
  // private String payState;
  // //요청 고유 번호/결제 고유 번호/가맹점 코드/결제상태(정기,단건)
  // private String aid, tid, cid, sid;
  // //가맹점 주문번호/가맹점 회원 id/ 결제 수단, CARD 또는 MONEY 중 하나
  // private String partner_order_id, partner_user_id, payment_method_type;
  // //결제 금액 정보
  // private AmountVO amount;  
  // //결제 상세 정보, 결제수단이 카드일 경우만 포함
  // private CardVO card_info; 
  // //상품 이름/상품 코드/결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용
  // private String item_name, item_code, payload;
  // //상품 수량
  // private Integer quantity;
  // //결제 준비 요청 시각/결제 승인 시각
  // private Date created_at, approved_at;
}
