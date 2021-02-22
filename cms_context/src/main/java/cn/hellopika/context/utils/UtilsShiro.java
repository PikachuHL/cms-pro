package cn.hellopika.context.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

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

    /**
     * 获取 Subject
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }



}
