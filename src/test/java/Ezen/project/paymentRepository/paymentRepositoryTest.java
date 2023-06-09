package Ezen.project.paymentRepository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Ezen.project.domain.Payment;
import Ezen.project.repository.PaymentRepository;

@SpringBootTest
public class paymentRepositoryTest {
  
@Autowired
PaymentRepository paymentRepository;

@Test
@DisplayName("결제 정보 저장 테스트")
public void createPaymentTest(){
  Payment payment = new Payment();
  payment.setUserId("회원1");
  payment.setReservationId(1L);
  payment.setPayType("카드");
  payment.setPayTime(LocalDateTime.now());
  payment.setPayState("결제완료");
  //save() 해줘야함.
}

}
