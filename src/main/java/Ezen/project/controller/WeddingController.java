package Ezen.project.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.DTO.UserDTO;
import Ezen.project.DTO.WeddingCommentDTO;
import Ezen.project.DTO.WeddingDTO;
import Ezen.project.service.UserService;
import Ezen.project.service.WeddingCommentService;
import Ezen.project.service.WeddingService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WeddingController {

    private final WeddingService weddingService;
    private final UserService userService;
    private final WeddingCommentService weddingCommentService;

    // 웨딩 문의 폼 전달 하기 전 검증 html 띄우기
    @GetMapping("wedding/save")
    public String saveForm(Model model, HttpSession session) {
        return "wedding/weddingVerification";
    }

    // 검증 후 폼 받기
    @GetMapping("/wedding/weddingForm")
    public String verification(Model model, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("user", userService.findById(userId));
        return "wedding/weddingForm";
    }

    // 웨딩 문의 폼 전달
    @PostMapping("wedding/save")
    public String save(@ModelAttribute WeddingDTO weddingDTO) {
        Long saveId = weddingService.save(weddingDTO);
        return "redirect:/wedding/" + saveId;
    }

    // 웨딩 문의 게시판 보기
    @GetMapping("weddingBoard")
    public String findAll(Model model) {
        List<WeddingDTO> weddingDTOList = weddingService.findAll();
        // List<WeddingDTO> weddingDTOList = weddingService.findAll(Sort.by(Sort.Direction.DESC , "weddingWriteDate"));
        model.addAttribute("weddingList", weddingDTOList);
        return "/wedding/weddingBoard";
    }

    // 문의글 상세보기
    @GetMapping("/wedding/{id}")
    public String findById(@PathVariable Long id, Model model, HttpSession session) {

        WeddingDTO weddingDTO = weddingService.findById(id);
        UserDTO user = userService.findById(weddingDTO.getUserId());

        Long userId = (Long) session.getAttribute("userId");

        UserDTO userName = userService.findById(userId);
        WeddingDTO weddingDTOUser = new WeddingDTO();
        weddingDTOUser.setUserName(userName.getUserName());

        /* 댓글 목록 가져오기 */
        List<WeddingCommentDTO> weddingCommentDTOList = weddingCommentService.findAll(id);
        model.addAttribute("commentList", weddingCommentDTOList);

        model.addAttribute("wedding", weddingDTO);
        model.addAttribute("logInUser", user);
        model.addAttribute("commentWriter", weddingDTOUser);
        System.out.println("유저 이름값 : "+ user.getUserName());

        return "/wedding/detail";
    }

    // 문의글 삭제
    @GetMapping("/wedding/delete/{id}")
    public String delete(@PathVariable Long id) {
        weddingService.delete(id);
        return "redirect:/weddingBoard";
    }

    @GetMapping("wedding/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<WeddingDTO> weddingList = weddingService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < weddingList.getTotalPages()) ? startPage + blockLimit - 1 : weddingList.getTotalPages();

        model.addAttribute("weddingList", weddingList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/wedding/weddingPaging";
    }

    

}
