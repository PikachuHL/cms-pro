package cn.hellopika.portal.security.filter;

import cn.hellopika.context.foundation.Result;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CmsAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch("/admin/cms/login.do", request);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        Thread.sleep(3000);
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 设置响应体数据类型
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
        return false;
    }
}
