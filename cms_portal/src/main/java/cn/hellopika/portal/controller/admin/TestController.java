package cn.hellopika.portal.controller.admin;

import cn.hellopika.service.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("test")
    public String test(){
       return "/admin/test/test";
    }
}
