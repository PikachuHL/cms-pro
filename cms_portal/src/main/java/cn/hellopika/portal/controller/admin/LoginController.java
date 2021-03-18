package cn.hellopika.portal.controller.admin;

import cn.hellopika.context.utils.UtilsShiro;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 后台登录页控制器
 */
@Controller
@Slf4j
public class LoginController {

    private static final String IMAGE_CAPTCHA_SUFFIX = "image_captcha";

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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
    public void toCaptcha(HttpServletResponse response) {
        // 获取验证码的文本
        String text = captchaProducer.createText();

        /**
         * 把验证码的文本存到 redis 中
         * Key 使用 SessionId + "image_captcha", 保证一次请求只有一个验证码
         * 失效时间设置为 60s
         */
        redisTemplate.opsForValue().set(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX, text, 60, TimeUnit.SECONDS);

        // 根据验证码生成图片
        BufferedImage image = captchaProducer.createImage(text);

        /**
         * 输出图片
         *
         * 使用 try-with-resource, 在try中声明流, 在try执行完之后可以自动关闭流
         */
        try (ServletOutputStream outputStream = response.getOutputStream();) {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            log.error("验证码生成错误");
        }
    }


}
