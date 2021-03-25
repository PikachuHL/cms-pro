package cn.hellopika.context.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
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

    /**
     * 使用 SHA-256 加密用户密码
     * @param password  用户密码
     * @param salt      盐
     * @return          加密后的字符串
     */
    public static String encryptPassword(String password, String salt){
        // 第一个参数为：加密算法名称
        // 第二个参数为：迭代次数
        return new SimpleHash("SHA-256", password, salt, 1024).toHex();
    }

    /**
     * 生成 密码盐
     * @return
     */
    public static String generateSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }



}
