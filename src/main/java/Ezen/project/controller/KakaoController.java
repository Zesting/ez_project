package Ezen.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class KakaoController {
    @RequestMapping(value="path", method=RequestMethod.GET)
    public ModelAndView kakaologin( 
        @RequestParam(value = "code", required = false) String code) throws Throwable {
            System.out.println("param : "+code);
        return null;
    }
    
}
