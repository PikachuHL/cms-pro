package cn.hellopika.context.utils;

import cn.hellopika.context.foundation.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 获取 request 和 response
 * 获取 访问者ip地址
 */
@Slf4j
public class UtilsHttp {

    private static final String HEADER_REAL_IP = "X-Real-IP";
    private static final String UNKNOWN = "unknown";
    private static final String SLASH = "../";
    private static final String BACKSLASH = "..\\";
    private static final String HEADER_FORWARDED_FOR = "X-Forwarded-For";
    private static final String COMMA = ",";
    private static final String IP_EMPTY = "0:0:0:0:0:0:0:1";
    private static final String IP_LOOP = "127.0.0.1";

    // 分页相关
    private static final int DEFAULT_SIZE = 10;
    private static final String PAGE_SIZE = "pageSize";
    private static final String PAGE_CURRENT = "pageCurrent";
    private static final String ORDER_BY = "orderBy";


    /**
     * 获取 request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取 response
     * @return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }

    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddress()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddress()。
     *
     * @return string
     */
    public static String getRemoteAddress() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader(HEADER_REAL_IP);
        if (!StringUtils.isBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip.contains(SLASH) || ip.contains(BACKSLASH)) {
                return "";
            }
            return ip;
        }
        ip = request.getHeader(HEADER_FORWARDED_FOR);
        if (!StringUtils.isBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(COMMA);
            if (index != -1) {
                ip = ip.substring(0, index);
            }
            if (ip.contains(SLASH) || ip.contains(BACKSLASH)) {
                return "";
            }
            return ip;
        } else {
            ip = request.getRemoteAddr();
            if (ip.contains(SLASH) || ip.contains(BACKSLASH)) {
                return "";
            }
            if (ip.equals(IP_EMPTY)) {
                ip = IP_LOOP;
            }
            return ip;
        }
    }

    /**
     * 根据请求类型响应不同的异常
     *
     * @param path  跳转路径
     * @param info  错误信息
     * @return      Result
     */
    public static Result<String> responseException(String path, String info){
        HttpServletRequest request = getRequest();

        //如果不是ajax请求, 就跳转页面; 如果是ajax请求, 就返回json
        if(Objects.isNull(request.getHeader("X-Requested-With"))){
            HttpServletResponse response = getResponse();
            try {
                response.sendRedirect(request.getServletPath() + path);
                return null;
            } catch (IOException e) {
                log.error("异常跳转失败");
            }
        }

        return Result.failed(info);
    }


    /**
     * 通过 request 获取前端传来的分页相关信息，并封装到 MyPageInfo 类中
     * @return MyPageInfo
     */
    public static MyPageInfo getPageInfo(){
        HttpServletRequest request = getRequest();

        String pageSize = request.getParameter(PAGE_SIZE);
        String pageCurrent = request.getParameter(PAGE_CURRENT);

        return new MyPageInfo(StringUtils.isNotBlank(pageSize)?Integer.parseInt(pageSize):1,
                StringUtils.isNotBlank(pageCurrent)?Integer.parseInt(pageCurrent):10,
                ORDER_BY);
    }

    // 定义一个内部类，封装前端传过来的分页相关信息
    public static final class MyPageInfo{
        private int pageSize;
        private int pageCurrent;
        private String orderBy;

        public MyPageInfo(int pageSize, int pageCurrent, String orderBy) {
            this.pageSize = pageSize;
            this.pageCurrent = pageCurrent;
            this.orderBy = orderBy;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageCurrent() {
            return pageCurrent;
        }

        public void setPageCurrent(int pageCurrent) {
            this.pageCurrent = pageCurrent;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }
    }
}
