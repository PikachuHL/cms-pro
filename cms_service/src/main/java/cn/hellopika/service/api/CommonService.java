package cn.hellopika.service.api;

public interface CommonService {

    /**
     * 获取 验证码
     */
    void imageCaptcha();

    /**
     * 验证 验证码
     */
    String verifyImageCaptcha(String captcha);
}
