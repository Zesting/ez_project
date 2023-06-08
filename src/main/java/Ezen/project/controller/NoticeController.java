package Ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Ezen.project.domain.NoticeDomain;
import Ezen.project.dto.NoticeDTO;
import Ezen.project.service.NoticeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
// @RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("notice")
    public String notice(){
        return "notice";
    }


    @GetMapping("/notice/save")
    public String saveForm(){
        return "noticeSave";
    }

    @PostMapping("/notice/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO){
        noticeService.save(noticeDTO);
        return "notice";
    }
    
}
