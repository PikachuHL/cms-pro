package cn.hellopika.context.advice;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.context.utils.UtilsHttp;
import cn.hellopika.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class UnifiedExceptionHanlder {

    // 处理 ConstraintViolationException 异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<String> constraintViolationExceptionHandler(ConstraintViolationException e){
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> item:constraintViolations){
            log.error(item.getPropertyPath().toString()+item.getMessage());
            return UtilsHttp.responseException("/error.do", item.getMessage());
        }

        return UtilsHttp.responseException("/error.do", "未知异常");
    }

    // 处理 BusinessException 异常
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<String> businessExceptionHandler(BusinessException e){
        return Result.failed(e.getMsg());
    }
}
