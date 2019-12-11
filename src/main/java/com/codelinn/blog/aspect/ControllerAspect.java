package com.codelinn.blog.aspect;

import com.codelinn.blog.common.Constant;
import com.codelinn.blog.service.MetricService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.joda.time.DateTime;
import com.codelinn.blog.model.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Class Name : com.codelinn.blog.aspect
 * Description :Controller请求日志切面
 *
 * @author : cailinfeng
 * Date : 2018/9/5 15:10
 */
@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Autowired
    private MetricService metricService;

    @Pointcut("execution (public * com.codelinn.blog.controller..*(..))")
    public void doLog() {
    }

    @Before(value = "doLog()")
    public void doBefore(JoinPoint joinpoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        logger.info("request uri={{}}, method={{}}, ip={{}}", request.getRequestURI(), request.getMethod(), ip);
        Metric metric = new Metric();
        metric.setCol(ip);
        DateTime now = DateTime.now();
        String nowStr = now.toString("yyyyMMdd");
        metric.setDate(nowStr);
        metric.setType(Constant.MetricType.VISIT.name());
        if(metricService.count(metric) == 0){
            metric.setQuota("1");
            metricService.insert(metric);
        }
        metric = new Metric();
        metric.setCol(ip);
        metric.setQuota(request.getRequestURI());
        metric.setType(Constant.MetricType.URL.name());
        metric.setDate(nowStr);
        metricService.insert(metric);
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
