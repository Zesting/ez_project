package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.DTO.NoticeDTO;
import Ezen.project.service.NoticeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
// @RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("notice")
    public String notice(){
        return "/notice/notice";
    }

    @GetMapping("/notice/save")
    public String saveForm(){
        return "/notice/noticeSave";
    }

    @PostMapping("/notice/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO){
        noticeService.save(noticeDTO);
        return "/notice/notice";
    }

    @GetMapping("/notice/list")
    public String findAll(Model model){
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
        return "/notice/noticeList";
    }

    @GetMapping("/notice/list/{id}")
    public String findById(@PathVariable Long id, Model model){
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("notice", noticeDTO);
        return "/notice/detail";
    }

    @GetMapping("/notice/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("noticeUpdate", noticeDTO);
        return "/notice/update";
    }

    @PostMapping("/notice/update")
    public String update(@ModelAttribute NoticeDTO noticeDTO, Model model){
        NoticeDTO notice = noticeService.update(noticeDTO);
        model.addAttribute("notice", notice);
        return "/notice/detail";
    }

    @GetMapping("/notice/delete/{id}")
    public String delete(@PathVariable Long id){
        noticeService.delete(id);
        return "redirect:/notice/list";
    }

}
