package cn.hellopika.service.impl;

import cn.hellopika.context.utils.UtilsHttp;
import cn.hellopika.context.utils.UtilsShiro;
import cn.hellopika.service.api.CommonService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    private static final String IMAGE_CAPTCHA_SUFFIX = "image_captcha";

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 获取 验证码
     */
    @Override
    public void imageCaptcha() {

        // 获取验证码的文本
        String text = captchaProducer.createText();

        /**
         * 把验证码的文本存到 redis 中
         * Key 使用 SessionId + "image_captcha", 保证一次请求只有一个验证码
         * 失效时间设置为 60s
         */
        redisTemplate.opsForValue().set(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX, text, 60, TimeUnit.SECONDS);

        // 获取 response
        HttpServletResponse response = UtilsHttp.getResponse();

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

    /**
     * 验证 验证码
     *
     * @param captcha
     * @return
     */
    @Override
    public String verifyImageCaptcha(String captcha) {
        // 从 redis 中获取到验证码
        String redisCaptcha = redisTemplate.opsForValue().get(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX);
        if (Objects.isNull(redisCaptcha)) {
            return "验证码已失效, 请重新获取";
        }

        if (!StringUtils.equals(captcha, redisCaptcha)) {
            return "验证码输入错误";
        }

        return null;
    }
}
