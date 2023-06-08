package Ezen.project.repository;

import java.util.List;
import java.util.Optional;

import Ezen.project.domain.AdminPage;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaAdminPageRepository implements AdminPageRepository{

  private final EntityManager em;// 엔티티 매니저 생성 또는 주입

  @Override
  public AdminPage save(AdminPage adminPage) {
    em.persist(adminPage);
    return adminPage;
  }

  @Override
  public List<AdminPage> findByAll() {
    String jpql = "Select a from Payment a";
    List<AdminPage> result = em.createQuery(jpql, AdminPage.class).getResultList();
    return result;
  }

  @Override
  public Optional<AdminPage> findById(Long adminId) {
    
    return null;
  }

  @Override
  public Optional<AdminPage> findByName(String userId) {
    
    return null;
  }

  @Override
  public Optional<AdminPage> update(AdminPage adminPage) {
    
    return null;
  }

  @Override
  public Long delete(Long adminId) {
    
    return null;
  }
  
}
