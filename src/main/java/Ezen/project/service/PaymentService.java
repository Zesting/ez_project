package Ezen.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import Ezen.project.domain.Payment;
import Ezen.project.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;

  public Payment save(Payment payment) {
    paymentRepository.save(payment);
    return payment;
  }
  
  public List<Payment> findByAll() {
    return paymentRepository.findByAll();
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
