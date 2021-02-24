package cn.hellopika.context.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取 request 和 response
 * 获取 访问者ip地址
 */
public class UtilsHttp {

    private static final String HEADER_REAL_IP = "X-Real-IP";
    private static final String UNKNOWN = "unknown";
    private static final String SLASH = "../";
    private static final String BACKSLASH = "..\\";
    private static final String HEADER_FORWARDED_FOR = "X-Forwarded-For";
    private static final String COMMA = ",";
    private static final String IP_EMPTY = "0:0:0:0:0:0:0:1";
    private static final String IP_LOOP = "127.0.0.1";

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
}
