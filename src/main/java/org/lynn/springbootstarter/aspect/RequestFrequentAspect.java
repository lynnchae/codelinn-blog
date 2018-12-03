package org.lynn.springbootstarter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.lynn.springbootstarter.common.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Name : org.lynn.springbootstarter.aspect
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/12/1 16:34
 */
@Aspect
@Component
public class RequestFrequentAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    private static Map<String, Long> requestMap = new ConcurrentHashMap<>();

    @Pointcut("@annotation(org.lynn.springbootstarter.annotation.FrequentShielded)")
    public void requestAspect() {
    }

    @Around(value = "requestAspect()")
    public Object requestFrequenShielder(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Long nowVisitTime = System.currentTimeMillis();
        //超过1分钟的ip将被从map中移除
        List<Map.Entry<String, Long>> list = new ArrayList<>(requestMap.entrySet());
        for (Map.Entry<String, Long> entry : list) {
            if (nowVisitTime - entry.getValue() > 5000) {
                logger.info("remove 1 min ago ip from requestMap , ip {}", request.getRemoteAddr());
                requestMap.remove(entry.getKey());
            }
        }
        String ip = request.getRemoteAddr();
        if (requestMap.get(ip) == null) {
            requestMap.put(ip, nowVisitTime);
            return joinPoint.proceed();
        } else {
            Long preVisitTime = requestMap.get(ip);
            nowVisitTime = System.currentTimeMillis();
            requestMap.put(ip, nowVisitTime);
            if ((nowVisitTime - preVisitTime) < 2000) {
                return ResultEntity.error(2, "访问过于频繁！");
            }
        }
        return joinPoint.proceed();
    }

}
