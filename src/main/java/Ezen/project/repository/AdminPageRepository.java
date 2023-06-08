package Ezen.project.repository;

import java.util.List;
import java.util.Optional;

import Ezen.project.domain.AdminPage;

public interface AdminPageRepository {
  AdminPage save(AdminPage adminPage);

  List<AdminPage> findByAll();

  Optional<AdminPage> findById(Long adminId);

  Optional<AdminPage> findByName(String userId);

  Optional<AdminPage> update(AdminPage adminPage);

  Long delete(Long adminId);
}