package Ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.domain.NoticeDomain;
import Ezen.project.service.NoticeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(){
        return "notice";
    }

    @GetMapping("/notice/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("notice/save")
    public String save(@ModelAttribute NoticeDomain noticeDomain){
        noticeService.save(noticeDomain);
        
        return "notice";
    }
    
}
