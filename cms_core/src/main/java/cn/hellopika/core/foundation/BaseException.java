package cn.hellopika.core.foundation;

public class BaseException extends RuntimeException {

    private Integer code;
    private String msg;

    public BaseException(Integer code, String msg){
        // 用于不让自定义的业务异常写 栈信息(stackTrace)
        // 从而优化自定义异常的性能
        super(msg, null, false, false);

        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
