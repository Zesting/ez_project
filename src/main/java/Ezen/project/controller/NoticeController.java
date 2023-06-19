package Ezen.project.controller;

import java.io.IOException;
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

import Ezen.project.DTO.NoticeDTO;
import Ezen.project.service.NoticeService;
import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final UserService userService;

    //공지 메인페이지
    @GetMapping("notice")
    public String findAll(Model model){
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
        return "/notice/noticeList";
    }

    //공지글 작성 폼
    @GetMapping("/notice/save")
    public String saveForm(Model model, HttpSession session){
        return "/notice/noticeVerification";
    }

    //검증 후 폼 받기
    @GetMapping("/notice/noticeSave")
    public String verification(Model model, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("user", userService.findById(userId));
        return "notice/noticeSave";
    }

    //작성 폼 전달
    @PostMapping("/notice/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO) throws IllegalStateException, IOException{
        Long saveId = noticeService.save(noticeDTO);
        return "redirect:/notice/" + saveId;
    }
    
    //글 상세보기
    @GetMapping("/notice/{id}")
    public String findById(@PathVariable Long id, Model model, 
                            @PageableDefault(page=1) Pageable pageable){
        NoticeDTO noticeDTO = noticeService.findById(id);
        List<String> noticeFileId = noticeDTO.getStoredFileName();
        System.out.println(noticeFileId);
        model.addAttribute("imageName", noticeFileId);
        model.addAttribute("notice", noticeDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "/notice/detail";
    }

    //수정 작성 폼
    @GetMapping("/notice/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("noticeUpdate", noticeDTO);
        return "/notice/update";
    }

    //수정 폼 전달
    @PostMapping("/notice/update")
    public String update(@ModelAttribute NoticeDTO noticeDTO, Model model){
        NoticeDTO notice = noticeService.update(noticeDTO);
        model.addAttribute("notice", notice);
        return "/notice/detail";
    }

    //게시글 삭제
    @GetMapping("/notice/delete/{id}")
    public String delete(@PathVariable Long id){
        noticeService.delete(id);
        return "redirect:/notice/paging";
    }

    //페이징 처리 /notice/paging?page=1
    @GetMapping("/notice/paging")
    //세션 넣는다
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        // pageable.getPageNumber();
        Page<NoticeDTO> noticeList = noticeService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < noticeList.getTotalPages()) ? startPage + blockLimit - 1 : noticeList.getTotalPages();

        

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/notice/paging";
        
    }

}
