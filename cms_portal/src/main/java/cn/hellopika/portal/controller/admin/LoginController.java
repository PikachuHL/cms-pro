package cn.hellopika.portal.controller.admin;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private Producer captchaProducer;

    @GetMapping("/login.do")
    public String toLogin() {
        return "/admin/login";
    }

    // 生成验证码
    @GetMapping("/captcha.do")
    public void toCaptcha(HttpServletResponse httpServletResponse) {
        String text = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(text);

        // 使用 try-with-resource, 在try中声明流, 在try执行完之后可以自动关闭流
        try(ServletOutputStream outputStream = httpServletResponse.getOutputStream();) {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e){
            log.error("验证码生成错误");
        }
    }
}
