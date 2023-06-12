package Ezen.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Ezen.project.DTO.AdminPageDTO;
import Ezen.project.domain.AdminPage;
import Ezen.project.repository.AdminPageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminPageService {
  private final AdminPageRepository adminPageRepository;
  
  public void save(AdminPageDTO adminPageDTO){
    AdminPage adminPage = AdminPage.toSaveEntity(adminPageDTO);
    adminPageRepository.save(adminPage);
    System.out.println(adminPage);
  }

  public List<AdminPageDTO> findAll() {
    //adminPageList변수명은 다른것으로 바꿔도 됨
    List<AdminPage> adminPageList = adminPageRepository.findAll(); //findAll()로 레파지토리에서 무언가를 가져올때는 Entity형태로 가져옴
    List<AdminPageDTO> adminPageDTOList = new ArrayList<>();
    for(AdminPage adminPage : adminPageList){
      adminPageDTOList.add(AdminPageDTO.toAdminPageDTO(adminPage));
    }
    return adminPageDTOList;
  }
  
}
