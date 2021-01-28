package cn.hellopika.portal.controller.admin;

import cn.hellopika.service.api.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/login.do")
    public String toLogin() {
        return "/admin/login";
    }

    // 生成验证码
    @GetMapping("/captcha.do")
    public void toCaptcha() {
        commonService.imageCaptcha();
    }
}
