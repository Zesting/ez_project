package Ezen.project.controller;

import java.util.List;

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
        model.addAttribute("weddingList", weddingDTOList);
        return "/wedding/weddingBoard";
    }

    // 문의글 상세보기
    @GetMapping("/wedding/{id}")
    public String findById(@PathVariable Long id, Model model) {

        WeddingDTO weddingDTO = weddingService.findById(id);
        UserDTO user = userService.findById(weddingDTO.getUserId());

        /* 댓글 목록 가져오기 */
        List<WeddingCommentDTO> weddingCommentDTOList = weddingCommentService.findAll(id);
        model.addAttribute("commentList", weddingCommentDTOList);

        model.addAttribute("wedding", weddingDTO);
        model.addAttribute("logInUser", user);

        return "/wedding/detail";
    }

    // 문의글 삭제
    @GetMapping("/wedding/delete/{id}")
    public String delete(@PathVariable Long id) {
        weddingService.delete(id);
        return "redirect:/weddingBoard";
    }

}
