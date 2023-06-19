package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Ezen.project.DTO.UserDTO;
import Ezen.project.service.EmailService;
import Ezen.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    @GetMapping("/memberjoin")
    public String userJoin() {
        return "user/memberjoin";
    }

    @PostMapping("/user/memberjoin")
    // modelattribute 생략가능하나 사용하는게 좋음
    public String userSave(@ModelAttribute UserDTO userDTO) {
        System.out.println("userDTO = " + userDTO);

        userService.save(userDTO); // 회원가입
        return "user/login";
    }

    @GetMapping("/login")
    public String login(HttpSession session, HttpServletRequest request) {
        // 준희 수정(이전 페이지로 리턴(Get))
        session.setAttribute("previousURL", request.getHeader("Referer"));
        System.out.println("get에서의 Referer1 : " + request.getHeader("Referer"));
        return "user/login";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";

    }


    @PostMapping("/user/login")
    public String userLogin(@ModelAttribute UserDTO userDTO, HttpSession session) {
        // 로그인 아이디 비밀번호 일치여부확인
        UserDTO result = userService.login(userDTO);
        if (result != null) {
            session.setAttribute("userId", result.getId());
            // 준희 수정(이전 페이지로 리턴(Post))
            String previousURL = (String) session.getAttribute("previousURL");
            System.out.println("Post에서의 previousURL : " + previousURL);
            if (previousURL == null) {
                return "redirect:/";
            }
            /** 로그인 성공하면 이전 화면으로 리턴 */
            return "redirect:" + previousURL;
            // return "home"; (기존 정래형 코드)
        } else {
            return "user/login";
        }

    }

    // 아이디 중복체크
    @PostMapping("/userIdCheck")
    @ResponseBody
    public int userIdConfirm(@RequestParam("userId") String userId) throws Exception {
        return (userId.equals(userService.checkId(userId))) ? 1 : 0 ;
    }

    @PostMapping("/emailconfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("email") String email) throws Exception {

        String code = emailService.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
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
        Long id = (Long) session.getAttribute("userId");
        UserDTO userDTO = userService.update(id);
        model.addAttribute("updateuser", userDTO);
        return "user/userupdate";
    }

    @PostMapping("/user/update")
    public String update(@ModelAttribute UserDTO userDTO) {
        userService.userupdate(userDTO);
        return "redirect:/user/" + userDTO.getId();
    }

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);

        return "redirect:/userlist";
    }

}
