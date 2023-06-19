package Ezen.project.DTO;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PaymentDTO {
  // paymentCtroller에서 DTO를 셋팅함
  private Long id;
  private Long reservationId;// 예약 번호
  private String userId; // 회원 아이디
  private String userName; // 회원 이름
  private String roomName; // 룸 이름
  private int amount; // 가격
  private String userPhoneNumber; // 회원 전화번호


  // //결제 실패시 데이터베이스에 취소 내역 저장
  // public Payment toCancelSaveEntity(HttpSession session) {
  //   Payment payment = new Payment();
  //   Payment payreservation = (Payment) session.getAttribute("payReservation");
  //   payment.setId(null);  //결제 고유 번호
  //   payment.setTid("-"); // 카카오페이 결제 고유 번호
  //   payment.setUserId(""+session.getAttribute("userId")); // 회원 고유 번호
  //   payment.setReservationId(""+reservationId); // 예약 고유 번호
  //   payment.setRoomName(payreservation.getRoomName()); // 예약된 방 이름
  //   payment.setPayment_method_type(""); // 결제 방법 crad 또는 Money
  //   payment.setApproved_at(null); // 결제 승인 시간
  //   payment.setPayState("취소"); // 결제 상태
  //   payment.setKakaoPoint(0); //카카오 포인트 
  //   payment.setCardName("-");
  //   payment.setPaymentAmount(amount);//total_amount
  //   payment.setDiscountAmount(0);//결제금액 - 카카오포인트
  //   System.out.println("취소 payment:::::"+payment);
  //   return payment;
  // }

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
