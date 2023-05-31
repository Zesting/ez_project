package Ezen.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Ezen.project.domain.SampleDomain;
import Ezen.project.service.SampleService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 클래스의 생성자를 자동으로 생성하는 어노테이션(인자는 final 붙은 필드)
@Controller
public class SampleController {
    private final SampleService sampleService;

    @PostMapping("/create")
    public String createSample(SampleDomain form, Model model) {
        SampleDomain sampleDomain = new SampleDomain();
        sampleDomain.setSampleFiledName(form.getSampleFiledName());
        sampleDomain.setSampleFiledAge(form.getSampleFiledAge());
        sampleService.sampleJoin(sampleDomain);
        /* model.addAttribute("sampleDomain", sampleDomain); */
        return "sample";
    }

    @GetMapping("/list")
    public String sampleList(Model model) {
        List<SampleDomain> sampleList = sampleService.findByAll();
        model.addAttribute("sampleList", sampleList);
        return "list";
    }
}
