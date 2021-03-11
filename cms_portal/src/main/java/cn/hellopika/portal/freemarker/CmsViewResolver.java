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

        // 获取servlet路径(url-pattern中设置的: admin/cms/)
        String servletPath = request.getServletPath();

        /**
         * basePath 是项目的根路径, 代表webapp目录, 在html中引用相关资源的时候常用
         * adminPath 是根路径加上servlet路径, 后面跟上 Controller 中 requestMapping 的路径.
         */
        model.put("basePath", contextPath);
        model.put("adminPath", contextPath+servletPath);
    }
}
