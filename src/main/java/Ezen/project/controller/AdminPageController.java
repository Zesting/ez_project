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
import Ezen.project.DTO.PaymentInfoDTO;
import Ezen.project.DTO.UserDTO;
import Ezen.project.DTO.WeddingDTO;
import Ezen.project.domain.Payment;
import Ezen.project.service.AdminPageService;
import Ezen.project.service.NoticeService;
import Ezen.project.service.PaymentService;
import Ezen.project.service.UserService;
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
  private final PaymentService paymentService;

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
    //사용 x
  @PostMapping("/adminPageSave")
  public String save(@ModelAttribute AdminPageDTO adminPageDTO){
    System.out.println("adminPageDTO = "+ adminPageDTO);
    adminPageService.save(adminPageDTO);
    return "redirect:/adminPage";//나중에 보여줄 주소로 바꾸기 임시 테스트
  }

  //사용 x
  @GetMapping("/adminPageList")
  public String findAll(Model model){
    List<AdminPageDTO> adminPageList = adminPageService.findAll();
    model.addAttribute("adminPageList", adminPageList);
    return "admin/adminPageList";
  }
  //결제된 정보 리스트
  @GetMapping("/adminPaymentInfo/{id}")
  public String paymentInfo(HttpSession session, Model model, @PathVariable("id") Long payId) {
    Long userDTO = (Long) session.getAttribute("userId");
    Payment payment = paymentService.findById(payId).get();
    UserDTO logInUser = userService.findById(userDTO);
    // Entity -> DTO (DTO class에서 변환)
    PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO();
    paymentInfoDTO.setPayId(payment.getId());
    paymentInfoDTO.setUserId(payment.getUserId());
    paymentInfoDTO.setTid(payment.getTid());
    paymentInfoDTO.setUserName(logInUser.getUserName());// user
    paymentInfoDTO.setUserLoginId(logInUser.getUserId());// user
    paymentInfoDTO.setUserPhoneNumber(logInUser.getUserPhoneNumber());// user
    paymentInfoDTO.setOrderNumber(payment.getReservationId());
    paymentInfoDTO.setRoomName(payment.getRoomName());
    paymentInfoDTO.setRoomPrice(payment.getPaymentAmount());
    paymentInfoDTO.setQuantity(1);
    paymentInfoDTO.setFinalPrice(payment.getDiscountAmount());
    paymentInfoDTO.setPrice(payment.getPaymentAmount());
    paymentInfoDTO.setPoint(payment.getKakaoPoint());
    paymentInfoDTO.setPaymentMethod(payment.getPayment_method_type());
    paymentInfoDTO.setCardName(payment.getCardName());
    paymentInfoDTO.setApproved_at(payment.getApproved_at());
    paymentInfoDTO.setPayState(payment.getPayState());
    paymentInfoDTO.setUserAuthority(logInUser.getUserAuthority());
    model.addAttribute("payInfo", paymentInfoDTO);
    return "admin/adminPaymentInfo";
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

  // 공지 노페이징 맵핑
  @GetMapping("/noticeList")
  public String noticeList(Model model){
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
    return "/notice/adminNoticeList";
  }

  //게시글 삭제 (공지)
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

  //게시글 삭제 (웨딩)
    @GetMapping("/adminWedding/delete/{id}")
    public String deleteWedding(@PathVariable Long id){
        weddingService.delete(id);
        return "redirect:/adminPage";
    }

  

    // 웨딩 문의 페이징 매핑
  // @GetMapping("/weddingList")
  //   public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
  //       Page<WeddingDTO> weddingList = weddingService.paging(pageable);
  //       int blockLimit = 5;
  //       int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
  //       int endPage = ((startPage + blockLimit - 1) < weddingList.getTotalPages()) ? startPage + blockLimit - 1 : weddingList.getTotalPages();

  //       model.addAttribute("weddingList", weddingList);
  //       model.addAttribute("startPage", startPage);
  //       model.addAttribute("endPage", endPage);

  //       return "/wedding/adminWeddingPaging";
  //   }


  //공지사항 등록
  @GetMapping("/adminNoticeSave")
    public String adminSave(Model model, HttpSession session){
       Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("user", userService.findById(userId));
        return "/notice/adminNoticeSave";
    }

}

 
