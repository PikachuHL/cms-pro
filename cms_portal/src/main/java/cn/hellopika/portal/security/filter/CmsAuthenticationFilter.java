package cn.hellopika.portal.security.filter;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.service.api.CommonService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Objects;


public class CmsAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    private CommonService commonService;

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch("/admin/cms/login.do", request);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 设置响应体数据类型
        response.setContentType("application/json;charset=UTF-8");

        //获取验证码并执行验证
        String captcha = WebUtils.getCleanParam(request, "captcha");
        String verifyResult = commonService.verifyImageCaptcha(captcha);
        if (Objects.nonNull(verifyResult)) {
            response.getWriter().write(JSON.toJSONString(Result.failed(verifyResult)));
            return false;
        }

        response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
        return false;
    }
}
