package org.lynn.springbootstarter.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Class Name : org.lynn.springbootstarter.aspect
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/5 15:10
 */
@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution (public * org.lynn.springbootstarter.controller..*(..))")
    public void doLog() {
    }

    @Before(value = "doLog()")
    public void doBefore(JoinPoint joinpoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("request uri={{}}, method={{}}, ip={{}}", request.getRequestURI(), request.getMethod(), request.getRemoteAddr());
        logger.info("class.method={}#{}", joinpoint.getSignature().getDeclaringTypeName(), joinpoint.getSignature().getName());
        logger.info("args={}", joinpoint.getArgs());
    }

    @AfterReturning(pointcut = "doLog()", returning = "object")
    public void doAfterReturning(Object object) {
        logger.info("response={{}}", object);
    }

    @AfterThrowing(value = "doLog()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinpoint, Exception ex) {
        logger.info("class.method={}#{}, error={}", joinpoint.getSignature().getDeclaringTypeName(), joinpoint.getSignature().getName(),ex.getMessage());
        logger.error("error stack message: {}", ex);
    }


}
