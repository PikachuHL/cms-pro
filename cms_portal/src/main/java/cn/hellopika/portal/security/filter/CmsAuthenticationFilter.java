package cn.hellopika.portal.security.filter;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.context.utils.UtilsShiro;
import cn.hellopika.service.api.CommonService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
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

    /**
     * 执行登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
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

        // 执行登录
        AuthenticationToken token = this.createToken(request,response);
        Subject subject = UtilsShiro.getSubject();
        try{
            subject.login(token);

            response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
        }catch (UnknownAccountException e){
            response.getWriter().write(JSON.toJSONString(Result.failed("用户不存在")));
        }catch (IncorrectCredentialsException e){
            response.getWriter().write(JSON.toJSONString(Result.failed("用户名或密码错误")));
        }


        return false;
    }
}
