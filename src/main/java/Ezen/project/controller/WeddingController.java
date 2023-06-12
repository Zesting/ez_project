package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.DTO.UserDTO;
import Ezen.project.DTO.WeddingDTO;
import Ezen.project.service.UserService;
import Ezen.project.service.WeddingService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WeddingController {
    
    private final WeddingService weddingService;
    private final UserService userService;

    //웨딩 문의 폼 
    @GetMapping("wedding/save")
    public String saveForm(){
        return "/wedding/weddingForm";
    }  

    //웨딩 문의 폼 전달
    @PostMapping("wedding/save")
    public String save(@ModelAttribute WeddingDTO weddingDTO){
        Long saveId = weddingService.save(weddingDTO);
        return "redirect:/wedding/" + saveId;
    }


    //웨딩 문의 게시판 보기
    @GetMapping("weddingBoard")
    public String findAll(Model model){
        List<WeddingDTO> weddingDTOList = weddingService.findAll();
        model.addAttribute("weddingList", weddingDTOList);
        return "/wedding/weddingBoard";
    }

    //문의글 상세보기
    @GetMapping("/wedding/{id}")
    public String findById(@PathVariable Long id, Model model){


        WeddingDTO weddingDTO = weddingService.findById(id);
        UserDTO user = userService.findById(weddingDTO.getUserId());

        
        model.addAttribute("wedding", weddingDTO);
        model.addAttribute("logInUser", user);

        return "/wedding/detail";
    }

    //문의글 삭제
    @GetMapping("/wedding/delete/{id}")
    public String delete(@PathVariable Long id){
        weddingService.delete(id);
        return "redirect:/weddingBoard";
    }


}
