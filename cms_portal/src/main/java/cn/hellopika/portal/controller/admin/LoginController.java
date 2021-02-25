package cn.hellopika.portal.controller.admin;

import cn.hellopika.context.utils.UtilsShiro;
import cn.hellopika.service.api.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 后台登录页控制器
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/login.do")
    public String toLogin() {

        // 先判断是否登录, 如果已登录, 则直接跳转到首页
        Subject subject = UtilsShiro.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:index.do";
        }

        // 如果是未登录状态, 则跳转到登录页面
        return "/admin/login";
    }

    // 生成验证码
    @GetMapping("/captcha.do")
    public void toCaptcha() {
        commonService.imageCaptcha();
    }
}
