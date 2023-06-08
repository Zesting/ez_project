package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Ezen.project.domain.Payment;
import Ezen.project.service.PaymentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 클래스의 생성자를 자동으로 생성하는 어노테이션(인자는 final 붙은 필드)
@Controller
public class paymentController {
  private final PaymentService paymentService;


    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }
 

    //데이터베이스에 결제 리스트 확인
    @GetMapping("/paymentList")
    public String paymentList(Model model) {
      List<Payment> paymentList = paymentService.findByAll();
      model.addAttribute("paymentList", paymentList);
      System.out.println("getMapping() list 출력");
      return "payment/paymentList";
    }


    @GetMapping("/iamport")
    public String iamport(){
      return "payment";
    }
}
