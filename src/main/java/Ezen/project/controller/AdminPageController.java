package Ezen.project.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Ezen.project.DTO.AdminCountDTO;
import Ezen.project.DTO.NoticeDTO;
import Ezen.project.DTO.PaymentInfoDTO;
import Ezen.project.DTO.UserDTO;
import Ezen.project.DTO.WeddingDTO;
import Ezen.project.domain.Payment;
import Ezen.project.domain.Reservation;
import Ezen.project.domain.Room;
import Ezen.project.service.NoticeService;
import Ezen.project.service.PaymentService;
import Ezen.project.service.ReservationService;
import Ezen.project.service.RoomService;
import Ezen.project.service.UserService;
import Ezen.project.service.WeddingService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

//DTO -> Entity (Entity class에서 변환)
//Entity -> DTO (DTO class에서 변환)

@Controller
@RequiredArgsConstructor
public class AdminPageController {
  private final UserService userService;
  private final NoticeService noticeService;
  private final WeddingService weddingService;
  private final PaymentService paymentService;
  private final ReservationService reservationService;
  private final RoomService roomService;

  @GetMapping("/adminPage")
  public String adminPage(Model model, HttpSession session) {
    if (session.getAttribute("userId") == null) {
      return "redirect:/login";
    }
    List<UserDTO> userDTOList = userService.findAll();
    model.addAttribute("userList", userDTOList);
    Date today = new Date(System.currentTimeMillis());
    List<Reservation> checkInRoom = reservationService.findAllReservationByCheckIn(today);
    List<Room> allRoom = roomService.findAllRoom();

    System.out.println("allRoom -> " + allRoom);
    System.out.println("오늘 체크인 된 수:" + checkInRoom);
    List<String> nameList = new ArrayList<>();
    for (int i = 0; i < checkInRoom.size(); i++) {
      // System.out.println(checkInRoom.get(i).getRoomId()); //룸 ID 확인
      Room roomName = roomService.findRoomById(checkInRoom.get(i).getRoomId()).get();
      System.out.println(roomName.getRoomName()); // 룸 이름 확인
      nameList.add(roomName.getRoomName());
    }
    ;
    System.out.println(nameList);
    int earthCount = 0, marsCount = 0, jupiterCount = 0, mercuryCount = 0;
    for (int i = 0; i < nameList.size(); i++) {
      if (nameList.get(i).contains("Mercury")) {
        mercuryCount++;
      } else if (nameList.get(i).contains("Mars")) {
        marsCount++;
      } else if (nameList.get(i).contains("Earth")) {
        earthCount++;
      } else if (nameList.get(i).contains("Jupiter")) {
        jupiterCount++;
      }
    }
    System.out.println("Mercury 카운트: " + mercuryCount);
    System.out.println("Earth 카운트: " + earthCount);
    System.out.println("Mars 카운트: " + marsCount);
    System.out.println("Jupiter 카운트: " + jupiterCount);
    AdminCountDTO adminCountDTO = new AdminCountDTO();
    adminCountDTO.setMercuryCount(mercuryCount);
    adminCountDTO.setMarsCount(marsCount);
    adminCountDTO.setEarthCount(earthCount);
    adminCountDTO.setJupiterCount(jupiterCount);
    model.addAttribute("roomNameCount", adminCountDTO);
    return "admin/adminPage";
  }

  @GetMapping("/adminPageSave")
  public String saveForm() {

    return "admin/adminPageSave";
  }

  // 결제된 정보 리스트
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

  // 공지 노페이징 맵핑
  @GetMapping("/noticeList")
  public String noticeList(Model model) {
    List<NoticeDTO> noticeDTOList = noticeService.findAll();
    model.addAttribute("noticeList", noticeDTOList);
    return "/notice/adminNoticeList";
  }

  // 공지 페이징 매핑
  // @GetMapping("/noticeList")
  // public String noticeList(@PageableDefault(page = 1) Pageable pageable, Model
  // model){
  // // pageable.getPageNumber();
  // Page<NoticeDTO> noticeList = noticeService.paging(pageable);
  // int blockLimit = 5;
  // int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() /
  // blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
  // int endPage = ((startPage + blockLimit - 1) < noticeList.getTotalPages()) ?
  // startPage + blockLimit - 1 : noticeList.getTotalPages();

  // model.addAttribute("noticeList", noticeList);
  // model.addAttribute("startPage", startPage);
  // model.addAttribute("endPage", endPage);
  // return "/notice/adminNoticeListPaging";
  // }

  // 게시글 삭제
  @GetMapping("/adminNotice/delete/{id}")
  public String delete(@PathVariable Long id) {
    noticeService.delete(id);
    return "redirect:/adminPage";
  }


   //웨딩 리스트 조회
    @GetMapping("/weddingList")
    public String weddingList(Model model){
      List<WeddingDTO> weddingDTOList = weddingService.findAll();
      
      

      List<Map<String, Object>> listAll = new ArrayList<>();
      weddingDTOList.stream().forEach(r -> {
        Map<String, Object> addMap = new LinkedHashMap<>();
        addMap.put("userName", userService.findById(r.getUserId()).getUserName());
        addMap.put("weddingId", r.getWeddingId());
        addMap.put("weddingTitle", r.getWeddingTitle());
        addMap.put("weddingWriteDate", r.getWeddingWriteDate());

        listAll.add(addMap);
      });

       model.addAttribute("weddingList", weddingDTOList);
       model.addAttribute("allList", listAll);
        return "/wedding/adminWeddingList";
    }

  // 웨딩 문의 페이징 매핑
  // @GetMapping("/weddingList")
  // public String paging(@PageableDefault(page = 1) Pageable pageable, Model
  // model){
  // Page<WeddingDTO> weddingList = weddingService.paging(pageable);
  // int blockLimit = 5;
  // int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() /
  // blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
  // int endPage = ((startPage + blockLimit - 1) < weddingList.getTotalPages()) ?
  // startPage + blockLimit - 1 : weddingList.getTotalPages();

  // model.addAttribute("weddingList", weddingList);
  // model.addAttribute("startPage", startPage);
  // model.addAttribute("endPage", endPage);

  // return "/wedding/adminWeddingPaging";
  // }

  // 공지사항 등록
  @GetMapping("/adminNoticeSave")
  public String adminSave(Model model, HttpSession session) {
    Long userId = (Long) session.getAttribute("userId");
    model.addAttribute("user", userService.findById(userId));
    return "/notice/adminNoticeSave";
  }

}
