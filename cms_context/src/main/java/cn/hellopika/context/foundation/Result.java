package cn.hellopika.context.foundation;

import java.io.Serializable;

/**
 *
 * 定义一个类,用于给前端返回信息, 是前后端接口开发必须要有的
 *
 * @param <T> 返回的data的类型
 */

public class Result<T extends Serializable> implements Serializable {
    private int restCode;

    private String restInfo;

    private T data;

    // 定义静态方法, 使需要返回result的时候, 不用创建对象, 直接调用静态方法即可
    public static <W extends Serializable> Result<W> success(){
        return new Result<>(200);
    }

    public static <W extends Serializable> Result<W> success(String restInfo){
        return new Result<>(200, restInfo);
    }

    public static <W extends Serializable> Result<W> success(String restInfo, W data){
        return new Result<>(200, restInfo, data);
    }


    public static <W extends Serializable> Result<W> failed(){
        return new Result(500);
    }

    public static <W extends Serializable> Result<W> failed(String restInfo){
        return new Result(500, restInfo);
    }


    public Result(int restCode) {
        this.restCode = restCode;
    }

    public Result(int restCode, String restInfo) {
        this.restCode = restCode;
        this.restInfo = restInfo;
    }

    public Result(int restCode, String restInfo, T data) {
        this.restCode = restCode;
        this.restInfo = restInfo;
        this.data = data;
    }

    public int getRestCode() {
        return restCode;
    }

    public void setRestCode(int restCode) {
        this.restCode = restCode;
    }

    public String getRestInfo() {
        return restInfo;
    }

    public void setRestInfo(String restInfo) {
        this.restInfo = restInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
