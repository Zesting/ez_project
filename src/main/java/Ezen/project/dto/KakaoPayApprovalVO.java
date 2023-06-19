package Ezen.project.DTO;

import java.util.Date;

import Ezen.project.domain.Payment;
import lombok.Data;

@Data
public class KakaoPayApprovalVO {

  // response
  // amount와 card_info는 JSON Object로 전송받기 때문에 객체를 따로 만들어준다.

  // 요청 고유 번호/결제 고유 번호/가맹점 코드/결제상태(정기,단건)
  private String aid, tid, cid, sid;
  // 가맹점 주문번호/가맹점 회원 id/ 결제 수단, CARD 또는 MONEY 중 하나
  private String partner_order_id, partner_user_id, payment_method_type;
  // 결제 금액 정보
  private AmountVO amount;
  // 결제 상세 정보, 결제수단이 카드일 경우만 포함
  private CardVO card_info;
  // 상품 이름/상품 코드/결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용
  private String item_name, item_code, payload;
  // 상품 수량
  private Integer quantity;
  // 결제 준비 요청 시각/결제 승인 시각
  private Date created_at, approved_at;

  // 결제 성공시 
  // DTO -> Entity 로 변환
  public Payment toSaveEntity() {
    Payment payment = new Payment();
    payment.setId(null);  //결제 고유 번호
    payment.setTid(tid); // 카카오페이 결제 고유 번호
    payment.setUserId(partner_user_id); // 회원 고유 번호
    payment.setReservationId(partner_order_id); // 예약 고유 번호
    payment.setRoomName(item_name); // 예약된 방 이름
    payment.setPayment_method_type(payment_method_type); // 결제 방법 crad 또는 Money
    payment.setApproved_at(approved_at); // 결제 승인 시간
    payment.setPayState("완료"); // 결제 상태
    payment.setKakaoPoint(amount.getPoint()); //카카오 포인트 
    payment.setPaymentAmount(amount.getTotal());//total_amount
    payment.setDiscountAmount(amount.getTotal()-amount.getPoint());//결제금액 - 카카오포인트
    if(card_info == null){
      payment.setCardName("카카오페이");  //카카오페이로 결제 할경우.
      return payment; 
    }else{
      payment.setCardName(""+card_info.getPurchase_corp()); // 카드 정보
    }
    // System.out.println("데이터베이스 변환되어 저장되는지 확인"+payment);
    return payment;
  }
}
