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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 세션이 존재하지않으면 null을 반환
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return "redirect:/";
        } else {
            return "/";
        }
    }

    @PostMapping("/user/login")
    public String userLogin(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {
        // 로그인 아이디 비밀번호 일치여부확인
        UserDTO result = userService.login(userDTO);
        // 로그인 권한 0일반회원 1관리자
        int Authority = 0;
        if (result != null) {
            Authority = userService.findById(result.getId()).getUserAuthority();
            session.setAttribute("userId", result.getId());
            session.setAttribute("admin", Authority);
            // 준희 수정(이전 페이지로 리턴(Post))
            String previousURL = (String) session.getAttribute("previousURL");
            System.out.println("Post에서의 previousURL : " + previousURL);
            if (previousURL == null) {
                return "redirect:/";
            }
            /** 로그인 성공하면 이전 화면으로 리턴 */
            return "redirect:" + previousURL;
        } else {
            model.addAttribute("failmsg", "아이디 또는 비밀번호를 다시 확인해주세요");
            return "user/login";
        }
    }

    @GetMapping("/findpwd")
    public String findPwd() {
        return "user/findpwd";
    }

    // 세션 or url 둘중하나로 비밀번호 변경할 id 값 넘기기
    @PostMapping("/findpassword")
    public String findPassword(@ModelAttribute UserDTO userDTO,
            Model model, HttpSession session) {
        Long changeId = userService.findPassword(userDTO);
        if (changeId != null) {
            session.setAttribute("findPassword", changeId);
            return "user/changepassword";
        } else {
            model.addAttribute("failmsg", "아이디와 이름 그리고 이메일이 일치하지않습니다.");
            return "user/findpwd";
        }
    }

    @PostMapping("/pwCheck")
    @ResponseBody
    public int changePwView(@RequestParam("newPassword") String newPw,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("findPassword");
        System.out.println("newPw : " + newPw);
        System.out.println("originalpw : " + userService.findById(userId).getUserPassword());
        return newPw.equals(userService.findById(userId).getUserPassword()) ? 0 : 1;
    }

    @PostMapping("/changepassword")
    public String changePassword(
            @RequestParam("newPw") String newPassword,
            HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("findPassword");
        userService.pwUpdate(userId, newPassword);
        session.invalidate();

        return "redirect:/";
    }

    // 아이디 중복체크
    @PostMapping("/userIdCheck")
    @ResponseBody
    public int userIdConfirm(@RequestParam("userId") String userId) throws Exception {
        return (userId.equals(userService.checkId(userId))) ? 1 : 0;
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

    @ResponseBody
    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "/";
    }
}
        