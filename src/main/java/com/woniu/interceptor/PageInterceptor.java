package com.woniu.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.Properties;

//分页拦截器
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        RowBounds rowBounds = (RowBounds) metaObject.getValue("delegate.rowBounds");
        if (rowBounds != null && !rowBounds.equals(RowBounds.DEFAULT)) {
            String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");
            int offset = rowBounds.getOffset();
            int limit = rowBounds.getLimit();
            String okSql = originalSql + " limit " + offset + "," + limit;
            metaObject.setValue("delegate.boundSql.sql", okSql);
            metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        }
        Object object = invocation.proceed();
        return object;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
