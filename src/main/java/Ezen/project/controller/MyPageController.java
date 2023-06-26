package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Ezen.project.DTO.UserDTO;
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

    // 마이페이지에서 모든 결제 내역 리스트 (결제내역)
    @RequestMapping(value = "/user/myPayment", method = RequestMethod.GET)
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

    @RequestMapping(value = "/user/memberLeave", method = RequestMethod.GET)
    public String memberLeave() {
        return "user/memberleave";
    }

    @GetMapping("/user/{id}")
    public String findById() {
        return "user/mypage";
    }

    @RequestMapping(value = "/user/myinfo", method = RequestMethod.GET)
    public String myInfo(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("userId");
        UserDTO userDTO = userService.findById(id);
        model.addAttribute("user", userDTO);
        return "user/userdetail";
    }

    @RequestMapping(value = "/changeMyInfo", method = RequestMethod.GET)
    public String changeMyInfo() {
        return "user/changemyinfo";
    }

    @ResponseBody
    @PostMapping("/deleteUser")
    public String deleteUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        userService.delete(userId);
        session.invalidate();
        return "success";
    }

    @PostMapping("/myInfoPwCheck")
    @ResponseBody
    public int changePw(@RequestParam("newPassword") String newPw,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        System.out.println("newPw : " + newPw);
        System.out.println("originalpw : " + userService.findById(userId).getUserPassword());
        return newPw.equals(userService.findById(userId).getUserPassword()) ? 0 : 1;
    }

    @PostMapping("/myInfoChangePw")
    public String changePassword(
            @RequestParam("newPw") String newPassword,
            HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        userService.pwUpdate(userId, newPassword);
        session.invalidate();
        return "redirect:/";
    }
}
