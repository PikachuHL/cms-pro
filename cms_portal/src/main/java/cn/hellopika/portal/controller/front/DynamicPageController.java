package cn.hellopika.portal.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 前台首页控制器
 */
@Controller
public class DynamicPageController {

    @GetMapping("index.shtml")
    public String toindex(){
        return "front/index";
    }
}
