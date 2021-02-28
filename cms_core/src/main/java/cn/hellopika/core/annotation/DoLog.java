package cn.hellopika.core.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoLog {
    // 日志内容
    String content();
}
