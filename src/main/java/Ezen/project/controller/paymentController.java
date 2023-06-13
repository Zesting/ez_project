package Ezen.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ezen.project.DTO.PaymentDTO;
import Ezen.project.DTO.RoomDTO;
import Ezen.project.DTO.UserDTO;
import Ezen.project.domain.Payment;
import Ezen.project.domain.Reservation;
import Ezen.project.service.KakaopayService;
import Ezen.project.service.PaymentService;
import Ezen.project.service.RoomService;
import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor // 클래스의 생성자를 자동으로 생성하는 어노테이션(인자는 final 붙은 필드)
@Controller
public class PaymentController {
  private final PaymentService paymentService;
  private final UserService userService;
  @Setter(onMethod_ = @Autowired)
  private KakaopayService kakaoPayService;
  private final RoomService roomService;

  @GetMapping("/kakaoPay")
  public void kakaoPayServiceGet() {

  }

  @PostMapping("/kakaoPay")
  public String kakaoPayService(HttpSession session, RoomDTO roomDTO) {
    log.info("kakaoPayService post.........................................");
    //세션 아이디 가져옴.
    Long userId = (Long) session.getAttribute("userId");
    Reservation reservation = (Reservation) session.getAttribute("reservation");
    UserDTO logInUser = userService.findById(userId);

    PaymentDTO paymentDTO = new PaymentDTO();
    paymentDTO.setReservationId(reservation.getReservationId());
    paymentDTO.setUserName(logInUser.getUserId());  //들어온것 확인함.
    paymentDTO.setRoomName(roomService.findRoomById(reservation.getRoomId()).get().getRoomName());//
    paymentDTO.setAmount(reservation.getFinalPrice());
    session.setAttribute("paymentDTO", paymentDTO);
    //셋팅해서 넣어야할 값들
  // private Long id;
  // private Long reservationId;//예약 번호
  // private String userName;    //회원 이름
  // private String rommName;    //룸 이름
  // private int amount;    //가격
  System.out.println("포스트맵핑/kakaopay dto 저장"+paymentDTO);
    return "redirect:" + kakaoPayService.kakaoPayReady(paymentDTO);
  }

  // 결제가 성공되었음.
  @GetMapping("/kakaoPaySuccess")
  public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session) {
    log.info("kakaoPaySuccess get............................................");
    log.info("kakaoPaySuccess pg_token : " + pg_token);
    model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token, session));
    model.addAttribute("payment", paymentService.findByAll());

    return "payment/kakaoPaySuccess";
  }

  @GetMapping("/kakaoPayCancel")
  // public String kakaoPayCancel(@RequestParam("amount") int
  // amount,@RequestParam("tid") String tid,Model model) {
  public String kakaoPayCancel() {

    return "payment/kakaoPayCancel";
  }

  @GetMapping("/kakaoPaySuccessFail")
  public String kakaoPaySuccessFail() {
    return "/payment/kakaoPaySuccessFail";
  }

  // 아래는 카카오 페이랑 관련 x 다른 api및 테스트하던 매핑.

  @GetMapping("/payment")
  public String payment() {
    return "payment/payment";
  }

  // @PostMapping("/payment")
  // public String kakaoPay() {

  //   return null;
  // }

  // Payment테이블에 결제 리스트 조회
  @GetMapping("/paymentList")
  public String paymentList(Model model) {
    List<Payment> paymentList = paymentService.findByAll();
    model.addAttribute("paymentList", paymentList);
    System.out.println("getMapping() list 출력");
    return "payment/paymentList";
  }

  // 아임포트 연동.. >>카카오페이 먼저 연동하고 해볼 생각.
  @GetMapping("/iamport")
  public String iamport() {
    return "payment/payment";
  }
}
