package Ezen.project.controller;

import java.util.List;

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
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor // 클래스의 생성자를 자동으로 생성하는 어노테이션(인자는 final 붙은 필드)
@Controller
public class PaymentController {
  private final PaymentService paymentService;
  private final UserService userService;
  private final KakaopayService kakaoPayService;
  private final RoomService roomService;

  //결제하기 버튼 눌렀을때.
  @PostMapping("/kakaoPay")
  public String kakaoPayService(HttpSession session, RoomDTO roomDTO) {
    log.info("kakaoPayService post.........................................");
    //세션 아이디가 없으면 로그인창으로 보내기
    if(session.getAttribute("userId") == null){
      return "redirect:/login";
    } 
    //세션 아이디 가져옴.
    Long userId = (Long) session.getAttribute("userId");
    //예약 정보 reservationController에서 세션에 저장하여 가져옴.
    if(session.getAttribute("payReservation")==null){
      return "redirect:/reservation/selectDate";      
    }
    Reservation reservation = (Reservation) session.getAttribute("payReservation");
    //로그인 되어 있는 userID를 가져옴
    UserDTO logInUser = userService.findById(userId);
    //새로운 객체를 만들어 필요한 값을 저장.
    PaymentDTO paymentDTO = new PaymentDTO();
    paymentDTO.setReservationId(reservation.getReservationId());
    paymentDTO.setUserName(logInUser.getUserId());
    //룸 ID로 찾아 예약된 룸아이디의 룸이름을 가져옴
    paymentDTO.setRoomName(roomService.findRoomById(reservation.getRoomId()).get().getRoomName());
    paymentDTO.setAmount(reservation.getFinalPrice());
    //다른 곳에서 DTO를 쓰기 위해 세션에 paymentDTO를 저장.
    session.setAttribute("paymentDTO", paymentDTO);
    
  System.out.println("포스트맵핑/kakaopay dto 저장"+paymentDTO);
    return "redirect:" + kakaoPayService.kakaoPayReady(paymentDTO);
  }

  // 결제가 성공되었을때.
  @GetMapping("/kakaoPaySuccess")
  public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session) {
    log.info("kakaoPaySuccess get............................................");
    log.info("kakaoPaySuccess pg_token : " + pg_token);
    model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token, session));
    model.addAttribute("payment", paymentService.findByAll());

    return "payment/kakaoPaySuccess";
  }

  //결제 진행 중 취소하였을 때.
  @GetMapping("/kakaoPayCancel")
  public String kakaoPayCancel() {
    //취소 되었다고 뷰로 표시
    //예약에 마지막 저장된 데이터를 삭제해야함. >> 준희가 결제 여부 만들어서 올려준다함.

    return "payment/kakaoPayCancel";
  }

  @GetMapping("/kakaoPaySuccessFail")
  public String kakaoPaySuccessFail() {
    return "/payment/kakaoPaySuccessFail";
  }

  // 아래는 카카오 페이랑 관련 x 다른 api및 테스트하던 매핑.

  //결제 버튼 있는 페이지
  @GetMapping("/payment")
  public String payment() {
    return "payment/payment";
  }

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
