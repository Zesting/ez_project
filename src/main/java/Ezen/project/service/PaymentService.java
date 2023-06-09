package Ezen.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Ezen.project.domain.Payment;
import Ezen.project.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;

  //결제 정보 저장
  public Payment save(Payment payment) {
    paymentRepository.save(payment);
    return payment;
  }
  
  //전체 결제 리스트 출력
  public List<Payment> findByAll() {
    System.out.println("전체 결제 리스트 출력");
    return paymentRepository.findAll();
  }

  public Optional<Payment> findById(Long payId) {
    return null;
    }
  
    public Optional<Payment> findByName(String userId) {
      return null;
    }
  
    public Optional<Payment> update(Payment payment) {
      return null;
    }
  
    public Long delete(Long payId) {
      return null;
      
    }
}