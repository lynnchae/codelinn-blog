package com.codelinn.blog.interceptor;

import lombok.extern.slf4j.Slf4j;
import com.codelinn.blog.util.IpUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class Name : org.lynn.springbootstarter.aspect
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/12/19 22:28
 */
@Component
@Slf4j
public class IpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean hit = IpUtils.isCnIp(request.getRemoteAddr());
        return hit;
    }
}
