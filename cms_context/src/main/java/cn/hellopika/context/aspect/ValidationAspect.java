package cn.hellopika.context.aspect;

import cn.hellopika.context.foundation.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

/**
 * 使用aop实现参数验证
 */
@Component
@Aspect
public class ValidationAspect {

    @Pointcut("@annotation(cn.hellopika.core.annotation.DoValid)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        for (Object arg : point.getArgs()) {
            if (arg instanceof BeanPropertyBindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    return Result.failed(result.getFieldError().getDefaultMessage());
                }
            }
        }

        return point.proceed();
    }
}
