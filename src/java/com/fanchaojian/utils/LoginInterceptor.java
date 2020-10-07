package com.fanchaojian.utils;

import com.alibaba.fastjson.JSON;
import com.fanchaojian.domain.BlogAdmin;
import com.fanchaojian.service.IBlogAdminService;
import com.mysql.jdbc.util.Base64Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;


/**
 * 用于登录拦截的过滤器
 * 需要拦截的路径包含save,delete,update,get
 * @author fanchaojian
 * @date 2020-9-28 - 17:00
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private IBlogAdminService blogAdminService ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String servletPath = request.getServletPath();
        System.out.println("请求路径："+servletPath) ;

        //判断请求路径中是否包含指定的字符串save,delete,update,get
        if(servletPath.contains("save") || servletPath.contains("delete") || servletPath.contains("update") || servletPath.contains("get")){
            System.out.println("匹配到方法") ;
            //获取header里的认证信息
            String result = JSON.toJSONString(ResultUtils.error(101, "登录认证失败"));
            String authorization = request.getHeader("Authorization");
            if(authorization == null){
                returnJson(response, result);
                return false ;
            }
            //base64解码
            Base64.Decoder decoder = Base64.getDecoder();
            String authText = new String(decoder.decode(authorization), "UTF-8");
            if(!authText.matches("^\\w+&{1}\\w+$")){
                System.out.println("登录认证失败，格式不正确") ;
                returnJson(response, result);
                return false ;
            }

            String username = authText.split("&")[0] ;
            String password = authText.split("&")[1] ;
            System.out.println("用户名："+username+",密码："+password) ;
            BlogAdmin admin = blogAdminService.login(username, password);
            if(admin != null){
                System.out.println("登录认证成功") ;
                return true ;
            }else{
                System.out.println("登录认证失败，用户名或密码错误"+result) ;
                returnJson(response, result);
                return false ;
            }
        }else{
            System.out.println("没有匹配到方法") ;
            return true ;
        }
    }

    private void returnJson(HttpServletResponse response, String result) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(result);

        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
