package Ezen.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ezen.project.domain.Payment;
import Ezen.project.service.KakaopayService;
import Ezen.project.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor // 클래스의 생성자를 자동으로 생성하는 어노테이션(인자는 final 붙은 필드)
@Controller
public class PaymentController {
  private final PaymentService paymentService;

  @Setter(onMethod_ = @Autowired)
  private KakaopayService kakaoPayService;

  @GetMapping("/kakaoPay")
  public void kakaoPayServiceGet() {

  }

  @PostMapping("/kakaoPay")
  public String kakaoPayService() {
    log.info("kakaoPayService post.........................................");

    return "redirect:" + kakaoPayService.kakaoPayReady();
  }

  //결제가 성공되었음.
  @GetMapping("/kakaoPaySuccess")
  public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model){
    log.info("kakaoPaySuccess get............................................");
    log.info("kakaoPaySuccess pg_token : " + pg_token);

    model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token));
    
    return "payment/kakaoPaySuccess";
  }

  @GetMapping("/kakaoPayCancel")
    // public String kakaoPayCancel(@RequestParam("amount") int amount,@RequestParam("tid") String tid,Model model) {
    public String kakaoPayCancel() {

        return "payment/kakaoPayCancel";
    }

  @GetMapping("/kakaoPaySuccessFail")
    public String kakaoPaySuccessFail(){
        return "/payment/kakaoPaySuccessFail";
    }


//아래는 카카오 페이랑 관련 x 다른 api및 테스트하던 매핑.
  
  @GetMapping("/payment")
  public String payment() {
    return "payment/payment";
  }

  @PostMapping("/payment")
  public String kakaoPay() {

    return null;
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
