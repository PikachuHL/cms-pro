package cn.hellopika.portal.interceptor;

import cn.hellopika.context.utils.UtilsHttp;
import cn.hellopika.context.utils.UtilsShiro;
import cn.hellopika.core.annotation.DoLog;
import cn.hellopika.service.api.CmsLogService;
import cn.hellopika.service.dto.CmsLogDto;
import cn.hellopika.service.dto.CmsUserDto;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 使用拦截器 统一添加日志(登录日志除外)
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CmsLogService cmsLogService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 把方法和DoLog注解绑定在一起
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        DoLog doLog = handlerMethod.getMethodAnnotation(DoLog.class);
        Optional.ofNullable(doLog).ifPresent(x->{
            // 获取登录url
            String url = request.getRequestURI();
            // 获取登录ip
            String loginIp = UtilsHttp.getRemoteAddress();
            // 获取注解中的content
            String content = doLog.content();

            // 将写日志放到线程池中, 提高性能
            threadPoolTaskExecutor.execute(() -> {
                // 获取用户的信息
                Subject subject = UtilsShiro.getSubject();
                CmsUserDto cmsUserDto = (CmsUserDto) subject.getPrincipal();
                cmsLogService.save(CmsLogDto.setCmsLogDto(cmsUserDto.getId(), cmsUserDto.getUsername(),
                        loginIp, url, content));
            });
        });

        super.postHandle(request, response, handler, modelAndView);
    }
}
