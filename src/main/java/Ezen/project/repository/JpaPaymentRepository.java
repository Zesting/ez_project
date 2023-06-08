package Ezen.project.repository;

import java.util.List;
import java.util.Optional;

import Ezen.project.domain.Payment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaPaymentRepository implements PaymentRepository{

  private final EntityManager em;// 엔티티 매니저 생성 또는 주입

  @Override
  public Payment save(Payment payment) {
    em.persist(payment);  // 엔티티를 영속 저장소에 저장
    return payment;
  }

  @Override
  public List<Payment> findByAll() {
    String jpql = "Select p from Payment p";
    List<Payment> result = em.createQuery(jpql, Payment.class).getResultList();
    return result;
  }

  @Override
  public Optional<Payment> findById(Long payId) {
    Payment payment = em.find(Payment.class, payId);
    return Optional.ofNullable(payment);
  }

  @Override
  public Optional<Payment> findByName(String userId) {
     List<Payment> result = em.createQuery("select p from Payment p where p.userId = : userId", Payment.class).setParameter("name", userId).getResultList();
     return result.stream().findAny();
  }

  @Override
  public Optional<Payment> update(Payment payment) {
    //결제는 수정이 필요없다.
    return null;
  }

  @Override
  public Long delete(Long payId) {
    //결제는 삭제가 필요없다.
    return null;
  }

}
