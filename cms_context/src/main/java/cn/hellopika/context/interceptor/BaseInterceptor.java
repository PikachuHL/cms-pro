package cn.hellopika.context.interceptor;


import cn.hellopika.core.foundation.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 *  MyBatis 的插件类
 */

@Intercepts(
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
)
public class BaseInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        switch (sqlCommandType){

            case INSERT:
                insert(invocation.getArgs()[1]);
                break;

            case UPDATE:
                update(invocation.getArgs()[1]);
                break;

            default:
                break;
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if(target instanceof Executor){
            Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 添加操作
     *
     * @param obj
     */
    private void insert(Object obj){
        if(obj instanceof BaseEntity){
            ((BaseEntity) obj).setCreateTime(LocalDateTime.now());
        }
    }

    /**
     * 修改操作
     *
     * @param obj
     */
    private void update(Object obj){
        if(obj instanceof BaseEntity){
            ((BaseEntity) obj).setUpdateTime(LocalDateTime.now());
        }
    }
}
