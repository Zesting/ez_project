package Ezen.project.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import Ezen.project.DTO.NoticeDTO;
import Ezen.project.service.NoticeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    //공지 메인페이지
    @GetMapping("notice")
    public String findAll(Model model){
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
        return "/notice/noticeList";
    }

    //공지글 작성 폼
    @GetMapping("/notice/save")
    public String saveForm(){
        return "/notice/noticeSave";
    }

    //작성 폼 전달
    @PostMapping("/notice/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO){
        Long saveId = noticeService.save(noticeDTO);
        return "redirect:/notice/" + saveId;
    }
    
    //글 상세보기
    @GetMapping("/notice/{id}")
    public String findById(@PathVariable Long id, Model model){
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("notice", noticeDTO);
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
        return "redirect:/notice";
    }

    //페이징 처리
    @GetMapping("/notice/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        // pageable.get
        return null;
    }

}
