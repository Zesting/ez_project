package Ezen.project.DTO;

import java.util.Date;

import lombok.Data;

//entity에 저장되어있는 정보를 가져와서 저장하고 꺼내볼수있게 하기.
@Data
public class PaymentInfoDTO {
  private Long payId;// 결제 고유번호// private Long id;
  private String userId;// 회원 고유번호//private String userId;
  private String tid;// private String tid; // 카카오페이 고유 번호
  private String userName;// 로그인된 사용자 이름
  private String userLoginId;// 로그인된 사용자 아이디
  private String userPhoneNumber;// 로그인된 사용자 번호
  private String orderNumber;// 주문번호 // private String reservationId; // 예약 고유 번호
  private Long roomId; //룸 고유 번호
  private String roomName;// 룸이름//private String roomName;
  private int roomPrice;// 룸가격
  private int quantity;// 수량
  private int finalPrice;// 최종결제금액// private int discountAmount; //할인된 금액
  private int price;// 포인트 할인전 금액// private int paymentAmount; // 원래 결제 금액
  private int point;// 카카오포인트 // private int kakaoPoint; //카카오 포인트
  private String paymentMethod;// 결제수단(방법)//private String payment_method_type;
  private String cardName;// 결제카드 이름// private String cardName; // 카드 정보
  private Date approved_at;// 승인일시// private Date approved_at; // 결제 시간
  private String payState;// 결제상태private String payState;
  private Integer userAuthority; //회원과 관리자 구분 1 또는 0

}
