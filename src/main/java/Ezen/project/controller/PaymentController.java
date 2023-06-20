package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ezen.project.DTO.PaymentDTO;
import Ezen.project.DTO.PaymentInfoDTO;
import Ezen.project.DTO.UserDTO;
import Ezen.project.domain.Payment;
import Ezen.project.domain.Reservation;
import Ezen.project.service.KakaopayService;
import Ezen.project.service.PaymentService;
import Ezen.project.service.ReservationService;
import Ezen.project.service.RoomService;
import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor // 클래스의 생성자를 자동으로 생성하는 어노테이션(인자는 final 붙은 필드)
@Controller
public class PaymentController {
  private final PaymentService paymentService;
  private final UserService userService;
  private final KakaopayService kakaoPayService;
  private final RoomService roomService;
  private final ReservationService reservationService;

  // 결제하기 버튼 눌렀을때.
  @PostMapping("/kakaoPay")
  public String kakaoPayService(HttpSession session, Model model) {
    log.info("kakaoPayService post.........................................");
    // 세션 아이디가 없으면 로그인창으로 보내기
    if (session.getAttribute("userId") == null) {
      return "redirect:/login";
    }
    // 세션 아이디 가져옴.
    Long userId = (Long) session.getAttribute("userId");
    // 예약 정보 reservationController에서 세션에 저장하여 가져옴.
    if (session.getAttribute("payReservation") == null) { // 검증 방법 바꾸기(준희참고)
      return "redirect:/verificationUser";
    }
    // 예약정보를 paymentDTO에 담기 위해 가져옴
    Reservation reservation = (Reservation) session.getAttribute("payReservation");
    // 중복결제 막기
    List<Payment> payments = paymentService.findByAll();
    for (Payment payment : payments) {
      if (payment.getReservationId().equals(String.valueOf(reservation.getReservationId()))) {
        System.out.println("payment.getReservationId() ::" + payment.getReservationId());
        System.out.println("reservation.getReservationId() ::" + reservation.getReservationId());
        System.out.println("결제가 완료된 요청입니다.!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "redirect:/pay";
      } else {
        System.out.println("payment.getReservationId() ::" + payment.getReservationId());
        System.out.println("reservation.getReservationId() ::" + reservation.getReservationId());
        System.out.println("값이 다르게 나왔습니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      }
    }
    System.out.println("List 비교문 끝나고 나옴 ");
    // 로그인 되어 있는 userID를 가져옴
    UserDTO logInUser = userService.findById(userId);
    // 새로운 객체를 만들어 필요한 값을 저장.
    PaymentDTO paymentDTO = new PaymentDTO();
    paymentDTO.setReservationId(reservation.getReservationId());
    paymentDTO.setUserId(logInUser.getUserId());
    paymentDTO.setUserName(logInUser.getUserName());
    paymentDTO.setUserPhoneNumber(logInUser.getUserPhoneNumber());
    // 룸 ID로 찾아 예약된 룸아이디의 룸이름을 가져옴
    paymentDTO.setRoomName(roomService.findRoomById(reservation.getRoomId()).get().getRoomName());
    paymentDTO.setAmount(reservation.getFinalPrice());
    // 다른 곳에서 DTO를 쓰기 위해 세션에 paymentDTO를 저장.
    session.setAttribute("paymentDTO", paymentDTO);
    System.out.println("포스트맵핑/kakaopay dto 저장" + paymentDTO);
    return "redirect:" + kakaoPayService.kakaoPayReady(paymentDTO);
  }

  // 결제가 성공되었을때.
  @GetMapping("/kakaoPaySuccess")
  public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session) {
    log.info("kakaoPaySuccess get............................................");
    log.info("kakaoPaySuccess pg_token : " + pg_token);
    model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token, session));
    model.addAttribute("payment", paymentService.findByAll());
    model.addAttribute("paymentDTO", session.getAttribute("paymentDTO"));
    return "payment/kakaoPaySuccess";
  }

  // 결제 진행 중 취소하였을 때.
  @GetMapping("/kakaoPayCancel")
  public String kakaoPayCancel(PaymentDTO paymentDTO, HttpSession session) {
    // 취소 되었다고 결제 데이터 베이스에 취소 상태해서 넣기
    log.info("kakaoPayCancel get............................................");
    return "payment/kakaoPayCancel";
  }

  @GetMapping("/kakaoPaySuccessFail")
  public String kakaoPaySuccessFail() {
    return "/payment/kakaoPaySuccessFail";
  }

  // 아래는 카카오 페이랑 관련 x 다른 api및 테스트하던 매핑.

  // 결제 버튼 있는 페이지
  @GetMapping("/payment")
  public String payment(HttpSession session, Model model) {
    // 결제 정보를 간단하게 띄워 보기
    Long logInUser = (Long) session.getAttribute("userId");

    Reservation reservation = (Reservation) session.getAttribute("payReservation");
    PaymentDTO paymentDTO = new PaymentDTO();
    paymentDTO.setReservationId(reservation.getReservationId());
    paymentDTO.setUserId(userService.findById(logInUser).getUserId());
    paymentDTO.setUserName(userService.findById(logInUser).getUserName());
    paymentDTO.setUserPhoneNumber(userService.findById(logInUser).getUserPhoneNumber());
    System.out.println("reservation 에 저장된 값은??????" + reservation);
    model.addAttribute("reservationInfo", reservation);
    return "payment/payment";
  }

  // Payment테이블에 결제 리스트 조회 [관리자 페이지]
  @GetMapping("/paymentList")
  public String paymentList(Model model, HttpSession session) {
    if (session.getAttribute("userId") == null) {
      return "redirect:/login";
    }
    List<Payment> paymentList = paymentService.findByAll();
    model.addAttribute("paymentList", paymentList);
    System.out.println("getMapping() paymentList 출력");
    return "payment/paymentList";
  }
  //마이페이지에서 모든 결제 내역 리스트 [ 마이페이지 ]
  @GetMapping("/paymentList/my")
  public String myPaymentList(HttpSession session, Model model) {
    Long userId = (Long) session.getAttribute("userId");
    String userName = userService.findById(userId).getUserName();
    List<Payment> payList = paymentService.findByUserId(userName);
    if (userId == null) {
      return "redirect:/login";
    }
    model.addAttribute("pay", payList);
    // 리스트값이 잘 나오는지 확인하는 작업
    // for (Payment payment : payList) {
    //   if (payment != null) {
    //     System.out.println("payment.getUserId() ::" + payment.getUserId());
    //     System.out.println(payList.toString());
    //     System.out.println("값이 같rp 나왔습니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    //   } else {
    //     System.out.println("값이 다르게 나왔습니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    //   }
    // }
    return "payment/myPaymentList";
  }

  // kakaoPaySuccessInfo
  // Payment 결제 별 정보 보기
  @GetMapping("/kakaoPaySuccessInfo/{id}")
  public String paymentInfo(HttpSession session, Model model, @PathVariable("id") Long payId) {
    Long userDTO = (Long) session.getAttribute("userId");
    Payment payment = paymentService.findById(payId).get();
    UserDTO logInUser = userService.findById(userDTO);
    // Entity -> DTO (DTO class에서 변환)
    PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO();
    paymentInfoDTO.setPayId(payment.getId());
    paymentInfoDTO.setUserId(payment.getUserId());
    paymentInfoDTO.setTid(payment.getTid());
    paymentInfoDTO.setUserName(logInUser.getUserName());// user
    paymentInfoDTO.setUserLoginId(logInUser.getUserId());// user
    paymentInfoDTO.setUserPhoneNumber(logInUser.getUserPhoneNumber());// user
    paymentInfoDTO.setOrderNumber(payment.getReservationId());
    paymentInfoDTO.setRoomName(payment.getRoomName());
    paymentInfoDTO.setRoomPrice(payment.getPaymentAmount());
    paymentInfoDTO.setQuantity(1);
    paymentInfoDTO.setFinalPrice(payment.getDiscountAmount());
    paymentInfoDTO.setPrice(payment.getPaymentAmount());
    paymentInfoDTO.setPoint(payment.getKakaoPoint());
    paymentInfoDTO.setPaymentMethod(payment.getPayment_method_type());
    paymentInfoDTO.setCardName(payment.getCardName());
    paymentInfoDTO.setApproved_at(payment.getApproved_at());
    paymentInfoDTO.setPayState(payment.getPayState());
    model.addAttribute("payInfo", paymentInfoDTO);
    return "/payment/kakaoPaySuccessInfo";
  }

  // 오류 발생시
  @GetMapping("/pay")
  public String error(Model model) {
    // model.addAttribute("msg", "결제 준비 요청에 null값이 있습니다.");
    model.addAttribute("msg", "결제가 완료된 요청입니다.");
    return "payment/pay";
  }

  // 아임포트 연동.. >>카카오페이 먼저 연동하고 해볼 생각.
  // @GetMapping("/iamport")
  // public String iamport() {
  //   return "payment/payment";
  // }

  // 결제 정보 삭제
  @GetMapping("/payment/delete/{id}")
  public String delete(@PathVariable("id") Long id, HttpSession session) {
    if (session.getAttribute("userId") == null) {
      return "redirect:/login";
    }
    Payment payment = paymentService.findById(id).get();
    reservationService.dropReservation(Long.parseLong(payment.getReservationId()));
    paymentService.delete(id);
    return "redirect:/adminPage";
  }
}
