package Ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import Ezen.project.service.SampleService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 클래스의 생성자를 자동으로 생성하는 어노테이션(인자는 final 붙은 필드)
@Controller
public class SampleController {
    private final SampleService sampleService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
