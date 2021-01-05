package cn.hellopika.portal.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class CmsViewResolver extends FreeMarkerView {
    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        // 获取项目的根路径
        String contextPath = request.getContextPath();

        //
        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();

        model.put("basePath", contextPath);
        model.put("adminPath", contextPath+servletPath);
    }
}
