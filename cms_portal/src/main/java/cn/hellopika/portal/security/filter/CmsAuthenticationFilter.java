package cn.hellopika.portal.security.filter;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.context.utils.UtilsHttp;
import cn.hellopika.context.utils.UtilsShiro;
import cn.hellopika.service.api.CmsLogService;
import cn.hellopika.service.api.CmsUserService;
import cn.hellopika.service.api.CommonService;
import cn.hellopika.service.dto.CmsLogDto;
import cn.hellopika.service.dto.CmsUserDto;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Objects;


public class CmsAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    private CommonService commonService;

    @Autowired
    private CmsUserService cmsUserService;

    @Autowired
    private CmsLogService cmsLogService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch("/admin/cms/login.do", request);
    }

    /**
     * 执行登录
     *
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

        PrintWriter writer = response.getWriter();

        //获取验证码并执行验证
        String captcha = WebUtils.getCleanParam(request, "captcha");
        String verifyResult = commonService.verifyImageCaptcha(captcha);
        if (Objects.nonNull(verifyResult)) {
            writer.write(JSON.toJSONString(Result.failed(verifyResult)));
            return false;
        }

        // 执行登录
        AuthenticationToken token = this.createToken(request, response);
        Subject subject = UtilsShiro.getSubject();
        try {
            subject.login(token);

            // 登录成功后保存日志
            onLoginSuccess(token, subject, request, response);

            writer.write(JSON.toJSONString(Result.success("登录成功")));
            writer.close();
        } catch (UnknownAccountException e) {
            writer.write(JSON.toJSONString(Result.failed("用户不存在")));
        } catch (IncorrectCredentialsException e) {
            writer.write(JSON.toJSONString(Result.failed("用户名或密码错误")));
        }

        writer.close();
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        // 获取登录url
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        // 获取登录ip
        String loginIp = UtilsHttp.getRemoteAddress();
        // 获取SessionId
        String sessionId = (String) UtilsShiro.getSession().getId();
        // 获取登录用户的信息
        CmsUserDto cmsUserDto = (CmsUserDto) subject.getPrincipal();

        // 将写日志和更新用户附表放到线程池中, 提高性能
        threadPoolTaskExecutor.execute(() -> {
            /**
             * 更新cms_user表
             */
            cmsUserDto.setLastLoginIp(loginIp);
            cmsUserDto.setSessionId(sessionId);
            cmsUserService.updateUser(cmsUserDto);
            /**
             * 更新cms_log表
             */
            cmsLogService.save(CmsLogDto.setCmsLogDto(cmsUserDto.getId(), cmsUserDto.getUsername(),
                    loginIp, url, "用户后台登录成功"));
        });

        return false;
    }
}
