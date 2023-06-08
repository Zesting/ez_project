package Ezen.project.repository;

import java.util.List;
import java.util.Optional;

import Ezen.project.domain.Payment;

public interface PaymentRepository {
  Payment save(Payment payment);

  List<Payment> findByAll();

  Optional<Payment> findById(Long payId);

  Optional<Payment> findByName(String userId);

  Optional<Payment> update(Payment payment);  //사용X

  Long delete(Long payId);  //사용X
}
