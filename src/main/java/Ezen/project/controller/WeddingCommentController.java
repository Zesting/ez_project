package Ezen.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Ezen.project.DTO.WeddingCommentDTO;
import Ezen.project.DTO.WeddingDTO;
import Ezen.project.service.WeddingCommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class WeddingCommentController {

    private final WeddingCommentService weddingCommentService;
    
    @PostMapping("/save")
    public ResponseEntity<?> save(@ModelAttribute WeddingCommentDTO weddingCommentDTO){
        System.out.println("weddingCommentDTO = " + weddingCommentDTO);
        Long saveResult = weddingCommentService.save(weddingCommentDTO);
        if (saveResult != null){
            //작성 성공하면 댓글목록을 가져와서 리턴
            //댓글목록: 해당 게시글의 댓글 전체 
            List<WeddingCommentDTO> weddingCommentDTOList = weddingCommentService.findAll(weddingCommentDTO.getWeddingId());

            return new ResponseEntity<>(weddingCommentDTOList, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
        
    }
}
 