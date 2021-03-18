package cn.hellopika.portal.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("index.do")
    public String toindex(){
        return "admin/index";
    }

    @GetMapping("error.do")
    public String toError(){
        return "admin/error";
    }

}
