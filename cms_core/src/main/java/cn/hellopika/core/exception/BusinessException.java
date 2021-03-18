package cn.hellopika.core.exception;

import cn.hellopika.core.foundation.BaseException;

/**
 * 业务中的自定义异常
 */
public class BusinessException extends BaseException {

    public BusinessException(Integer code, String msg) {
        super(code, msg);
    }

    public BusinessException(String msg) {
        super(500, msg);
    }
}
