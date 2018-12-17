package com.codelinn.blog.mybatis;

import com.codelinn.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Class Name : com.codelinn.blog.mybatis
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/20 3:14 PM
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
)})
@Slf4j
@Component
public class TagUpdateInterceptor implements Interceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private BlogService blogService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = statement.getBoundSql(invocation.getArgs()[1]);
        String sql = boundSql.getSql();
        Object result = invocation.proceed();
        if (sql.toLowerCase().contains("t_blog") && sql.toLowerCase().contains("tags")) {
            if (blogService == null) {
                blogService = applicationContext.getBean("blogService", BlogService.class);
            }
            blogService.refreshTaglistCache();
//            applicationContext.publishEvent(new TagUpdateEvent("tags"));
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
