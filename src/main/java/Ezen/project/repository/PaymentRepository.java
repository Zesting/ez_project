package Ezen.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ezen.project.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{

}

//JpaRepository 모듈을 사용을 위해 주석 처리하였음.
// public interface PaymentRepository {
//   Payment save(Payment payment);

//   List<Payment> findByAll();

//   Optional<Payment> findById(Long payId);

//   Optional<Payment> findByName(String userId);

//   Optional<Payment> update(Payment payment);  //사용X

//   Long delete(Long payId);  //사용X
// }
