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
import Ezen.project.DTO.WeddingDTO;
import Ezen.project.DTO.WeddingDTO;
import Ezen.project.service.AdminPageService;
import Ezen.project.service.NoticeService;
import Ezen.project.service.UserService;
import Ezen.project.service.WeddingService;
import Ezen.project.service.WeddingService;
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
  private final WeddingService weddingService;
  private final WeddingService weddingService;

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

  // 공지 노페이징 맵핑
  @GetMapping("/noticeList")
  public String noticeList(Model model){
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
    return "/notice/adminNoticeList";
  }

  // 공지 페이징 매핑
  // @GetMapping("/noticeList")
  // public String noticeList(@PageableDefault(page = 1) Pageable pageable, Model model){
  //       // pageable.getPageNumber();
  //       Page<NoticeDTO> noticeList = noticeService.paging(pageable);
  //       int blockLimit = 5;
  //       int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
  //       int endPage = ((startPage + blockLimit - 1) < noticeList.getTotalPages()) ? startPage + blockLimit - 1 : noticeList.getTotalPages();

  //       model.addAttribute("noticeList", noticeList);
  //       model.addAttribute("startPage", startPage);
  //       model.addAttribute("endPage", endPage);
  //   return "/notice/adminNoticeListPaging";
  // }

  //게시글 삭제
    @GetMapping("/adminNotice/delete/{id}")
    public String delete(@PathVariable Long id){
        noticeService.delete(id);
        return "redirect:/adminPage";
    }


  //웨딩 리스트 조회
    @GetMapping("/weddingList")
    public String weddingList(Model model){
       List<WeddingDTO> weddingDTOList = weddingService.findAll();
       model.addAttribute("weddingList", weddingDTOList);
        return "/wedding/adminWeddingList";
    }

  

    // 웨딩 문의 페이징 매핑
  @GetMapping("/weddingList")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<WeddingDTO> weddingList = weddingService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < weddingList.getTotalPages()) ? startPage + blockLimit - 1 : weddingList.getTotalPages();

        model.addAttribute("weddingList", weddingList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/wedding/adminWeddingPaging";
    }
>>>>>>> cdd755a6fc00653e98ef994e3f7cd62fafd9b193
}

 
