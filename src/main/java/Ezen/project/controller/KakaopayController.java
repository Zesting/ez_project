package Ezen.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import Ezen.project.service.KakaopayService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaopayController {
  // private final KakaopayService kakaopayService;

  // 결제 요청
  // @PostMapping("/ready")
  // public ResponseEntity readyToKakaoPay() {

  //   return KakaopayService.kakaopayReady();
  // }

  // // 결제 진행 중 취소
  // @GetMapping("/cancel")
  // public void cancel() {

  //   throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
  // }

  // // 결제 실패
  // @GetMapping("/fail")
  // public void fail() {

  //   throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
  // }
}
