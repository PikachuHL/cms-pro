package cn.hellopika.portal.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @GetMapping("index.do")
    public String toIndex(){
        return "/admin/permission/index";
    }
}
