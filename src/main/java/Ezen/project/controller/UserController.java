package Ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.DTO.UserDTO;
import Ezen.project.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/memberjoin")
    // modelattribute 생략가능하나 사용하는게 좋음
    public String userSave(@ModelAttribute UserDTO userDTO) {
        System.out.println("userDTO = " + userDTO);

        userService.save(userDTO);
        return "home";
    }

    @GetMapping("/userlist")
    public String userList() {
        return "user/userlist";
    }

    @GetMapping("/memberjoin")
    public String userJoin() {
        return "user/memberjoin";
    }
}
