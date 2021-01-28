package cn.hellopika.context.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Shiro 的工具类
 */
public class UtilsShiro {

    /**
     * 获取 Session
     *
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

}
