package cn.hellopika.service.impl;

import cn.hellopika.context.utils.UtilsShiro;
import cn.hellopika.service.api.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    private static final String IMAGE_CAPTCHA_SUFFIX = "image_captcha";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 获取验证码的功能需要在Controller中完成, 因为涉及到使用response做输出, 如果在Service中使用会出现一些不可预见的问题

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
