package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Ezen.project.domain.Payment;
import Ezen.project.service.PaymentService;
import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
    private final UserService userService;
    private final PaymentService paymentService;
    
    //마이페이지에서 모든 결제 내역 리스트 (결제내역)
  @RequestMapping(value="/paymentList/my", method = RequestMethod.GET)
  public String myPaymentList(HttpSession session, Model model) {
    Long userId = (Long) session.getAttribute("userId");
    String userName = userService.findById(userId).getUserName();
    List<Payment> payList = paymentService.findByUserId(userName);
    if (userId == null) {
      return "redirect:/login";
    }
    model.addAttribute("pay", payList);
    return "payment/myPaymentList";
  }
}
