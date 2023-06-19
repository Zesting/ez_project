package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.DTO.AdminPageDTO;
import Ezen.project.DTO.NoticeDTO;
import Ezen.project.DTO.UserDTO;
import Ezen.project.service.AdminPageService;
import Ezen.project.service.NoticeService;
import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

//DTO -> Entity (Entity class에서 변환)
//Entity -> DTO (DTO class에서 변환)


@Controller
@RequiredArgsConstructor
public class AdminPageController {
  private final AdminPageService adminPageService;
  private final UserService userService;
  private final NoticeService noticeService;

  @GetMapping("/adminPage")
  public String adminPage(Model model, HttpSession session){
    if(session.getAttribute("userId") == null) {
      return "redirect:/login";
    }
    List<UserDTO> userDTOList = userService.findAll();
    model.addAttribute("userList", userDTOList);
    return "admin/adminPage";
  }

  @GetMapping("/adminPageSave")
  public String saveForm(){
    
    return "admin/adminPageSave";
  }

  @PostMapping("/adminPageSave")
  public String save(@ModelAttribute AdminPageDTO adminPageDTO){
    System.out.println("adminPageDTO = "+ adminPageDTO);
    adminPageService.save(adminPageDTO);
    return "redirect:/adminPage";//나중에 보여줄 주소로 바꾸기 임시 테스트
  }

  @GetMapping("/adminPageList")
  public String findAll(Model model){
    List<AdminPageDTO> adminPageList = adminPageService.findAll();
    model.addAttribute("adminPageList", adminPageList);
    return "admin/adminPageList";
  }

  @GetMapping("/noticeList")
  public String noticeList(Model model){
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
    return "/notice/adminNoticeList";
  }

  //게시글 삭제
    @GetMapping("/adminNotice/delete/{id}")
    public String delete(@PathVariable Long id){
        noticeService.delete(id);
        return "redirect:/adminPage";
    }
}
