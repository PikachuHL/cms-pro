package cn.hellopika.portal.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class CmsViewResolver extends FreeMarkerView {
    //后台路径
    private static final String ADMIN_PATH = "/admin/cms/";


    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {

        /**
         * basePath 是项目的根路径, 代表webapp目录, 在html中引用相关资源的时候常用
         * adminPath 是根路径加上servlet路径, 后面跟上 Controller 中 requestMapping 的路径.
         */

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();

        // 那些页面添加 回退按钮
        List<String> includeGoBackList = Arrays.asList("add.do", "edit.do", "error.do");

        //就认为是后台请求路径
        if (requestURI.contains(ADMIN_PATH)) {
            model.put("adminPath", contextPath + servletPath);
        }
        //判断回退按钮
        includeGoBackList.forEach(x -> {
            if (requestURI.contains(x)) {
                model.put("goBack", true);
            }
        });
        model.put("basePath", contextPath);
    }
}

