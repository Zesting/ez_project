package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.domain.Payment;
import Ezen.project.service.PaymentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 클래스의 생성자를 자동으로 생성하는 어노테이션(인자는 final 붙은 필드)
@Controller
public class PaymentController {
  private final PaymentService paymentService;


    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

    @PostMapping("/payment")
    public String kakaoPay(){

      return null;
    }
 

    //Payment테이블에 결제 리스트 조회
    @GetMapping("/paymentList")
    public String paymentList(Model model) {
      List<Payment> paymentList = paymentService.findByAll();
      model.addAttribute("paymentList", paymentList);
      System.out.println("getMapping() list 출력");
      return "payment/paymentList";
    }


    //아임포트 연동.. 데이터값을 주지 않음 
    @GetMapping("/iamport")
    public String iamport(){
      return "payment/payment";
    }
}
