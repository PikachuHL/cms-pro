package cn.hellopika.portal.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Setter
@Getter
public class CmsUserFilter extends UserFilter {

    private String adminLoginUrl;
    private String adminPrefix;

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse resp) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        this.saveRequest(request);

        // 如果是后台链接, 则跳转到后台登录界面, 否则跳转到前台登录界面
        if(request.getRequestURI().startsWith(adminPrefix)){
            WebUtils.issueRedirect(request, resp, adminLoginUrl);
        }else{
            WebUtils.issueRedirect(request, resp, this.getLoginUrl());
        }


        return false;
    }
}
