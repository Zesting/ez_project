package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.DTO.UserDTO;
import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/memberjoin")
    public String userJoin() {
        return "user/memberjoin";
    }

    @PostMapping("/user/memberjoin")
    // modelattribute 생략가능하나 사용하는게 좋음
    public String userSave(@ModelAttribute UserDTO userDTO) {
        System.out.println("userDTO = " + userDTO);
        
        userService.save(userDTO); //회원가입
        return "user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/user/login")
    public String userLogin(@ModelAttribute UserDTO userDTO, HttpSession session) {
        // 로그인 아이디 비밀번호 일치여부확인
        UserDTO result = userService.login(userDTO);
        if (result != null) {
            session.setAttribute("userId", result.getUserId());
            return "home";
        } else {
            return "user/login";
        }
    }

    @GetMapping("/userlist")
    public String findAll(Model model) {
        List<UserDTO> userDTOList = userService.findAll();
        System.out.println(userDTOList);

        model.addAttribute("userList", userDTOList);
        return "user/userlist";
    }

    @GetMapping("/user/{id}")
    public String findById(@PathVariable Long id, Model model) {
        UserDTO userDTO = userService.findById(id);
        model.addAttribute("user", userDTO);
        return "user/userdetail";
    }

    @GetMapping("/userupdate")
    public String userUpdate(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        UserDTO userDTO = userService.update(userId);
        model.addAttribute("updateuser", userDTO);
        return "user/userupdate";
    }

    @PostMapping("/user/update")
    public String update(@ModelAttribute UserDTO userDTO) {
        userService.userupdate(userDTO);
        return "redirect:/user/" + userDTO.getId();
    }

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id){
        userService.delete(id);

        return "redirect:/userlist";
    }

}
