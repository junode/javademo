package com.demo.Filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by toutou on 2018/10/27.
 */
public class TestInterceptor  extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //request.getHeader(String) 从请求头中获取数据
        //从请求头中获取用户token（登陆凭证根据业务而定）
        Long userId= getUserId(request.getHeader("H-User-Token"));
        if (userId != null && checkAuth(userId,request.getRequestURI())){
            return true;
        }
        System.out.println(1);
        //这里的异常是我自定义的异常，系统抛出异常后框架捕获异常然后转为统一的格式返回给前端， 其实这里也可以返回false
        throw new Exception();
    }

    /**
     * 根据token获取用户ID
     * @param userToken
     * @return
     */
    private Long getUserId(String userToken){
        Long userId = null;
        return userId;
    }

    /**
     * 校验用户访问权限
     * @param userId
     * @param requestURI
     * @return
     */
    private boolean checkAuth(Long userId,String requestURI){
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(2);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(3);
    }
}