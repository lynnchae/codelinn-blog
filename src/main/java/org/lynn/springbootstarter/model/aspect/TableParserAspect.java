package org.lynn.springbootstarter.model.aspect;

import org.apache.ibatis.binding.MapperProxy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.lynn.springbootstarter.model.base.Entity;
import org.lynn.springbootstarter.model.core.SqlProvider;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * Class Name : org.lynn.springbootstarter.model.aspect
 * Description : tableName 解析器
 *
 * @author : cailinfeng
 * Date : 2018/9/5 10:03
 */
@Aspect
@Component
public class TableParserAspect {

    @Around("execution(* org.lynn.springbootstarter.dao.BaseDao.*(..))")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        if (proceedingJoinPoint.getArgs() != null
                && proceedingJoinPoint.getArgs().length > 0
                && proceedingJoinPoint.getArgs()[0] instanceof Entity) {
            SqlProvider.setModelClazz(proceedingJoinPoint.getArgs()[0].getClass());
        }else{
            //获取代理对象
            Field field = Proxy.class.getDeclaredField("h");
            field.setAccessible(true);
            Object target = field.get(proceedingJoinPoint.getTarget());
            Field mapperInterface = MapperProxy.class.getDeclaredField("mapperInterface");
            mapperInterface.setAccessible(true);
            Class<?> mapperClazz = (Class<?>)mapperInterface.get(target);
            String modelClassName = mapperClazz.getName().replace(".dao.",".model.").replace("Dao","");
            Class modelClazz = Class.forName(modelClassName);
            SqlProvider.setModelClazz(modelClazz);
        }
        Object o = proceedingJoinPoint.proceed();
        SqlProvider.removeModelClazz();
        return o;
    }

}
