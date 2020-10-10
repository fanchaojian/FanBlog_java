package com.fanchaojian.utils;

import com.alibaba.fastjson.JSON;
import com.fanchaojian.domain.BlogAdmin;
import com.fanchaojian.domain.User;
import com.fanchaojian.service.IBlogAdminService;
import com.fanchaojian.service.IUserService;
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
 * 平台所有的接口一共有三种不用的权限访问机制
 * 1：baseAdmin：系统管理员，可访问平台所有的接口
 * 2：baseUser：平台注册用户，可操作可供允许的平台注册用户所有的数据（如评论，回复功能）
 * 3：baseSelf：只能操作自己的数据（如查看和修改个人信息，删除自己的评论以及回复）
 *
 * @author fanchaojian
 *
 * @date 2020-9-28 - 17:00
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private IBlogAdminService blogAdminService ;

    @Autowired
    private IUserService userService ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String servletPath = request.getServletPath();
        String result = JSON.toJSONString(ResultUtils.error(101, "登录认证失败"));
        //判断请求路径中是否包含指定的字符串save,delete,update,get
        if(servletPath.contains("baseAdmin")){
             BlogAdmin blogAdmin = adminAuthorization(request,response,"BaseAdmin") ;
             if(blogAdmin == null){
                 returnJson(response, result);
                 return false ;
             }else{
                 return true ;
             }
        }else if(servletPath.contains("baseUser")){
            BlogAdmin blogAdmin = adminAuthorization(request,response,"BaseAdmin") ;
            User user = userAuthorization(request,response,"BaseUser") ;

            if(blogAdmin != null || user != null){
                return true ;
            }else{
                returnJson(response, result);
                return false ;
            }
        }else if(servletPath.contains("baseSelf")){
            BlogAdmin blogAdmin = adminAuthorization(request,response,"BaseAdmin") ;
            User user = userAuthorization(request,response,"BaseSelf") ;

            if(blogAdmin != null){
                return true ;
            }else if(user != null){
                //获取header中的userID参数
                int userID = Integer.parseInt(request.getParameter("userID")) ;
                User userById = userService.findById(userID);
                if(user.getUserId() == userById.getUserId()){
                    return true ;
                }else{
                    returnJson(response, result);
                    return false ;
                }
            }else{
                returnJson(response, result);
                return false ;
            }
        }else{
            return true ;
        }
    }

    private BlogAdmin  adminAuthorization(HttpServletRequest request, HttpServletResponse response,String headerStr)   {
        String authorization = request.getHeader(headerStr);
        if(authorization == null){
            return null  ;
        }
        String authText = "" ;
        //base64解码
        Base64.Decoder decoder = Base64.getDecoder();
        try{
            authText = new String(decoder.decode(authorization), "UTF-8");
        }catch(Exception e){
            return null ;
        }

        if(!authText.matches("^\\w+&{1}\\w+$")){
            return null ;
        }


        String username = authText.split("&")[0] ;
        String password = authText.split("&")[1] ;
        System.out.println("用户名："+username+",密码："+password) ;
        BlogAdmin admin = blogAdminService.login(username, password);
        return admin ;
    }


    private User userAuthorization(HttpServletRequest request,HttpServletResponse response,String headerStr)  {

        String authorization = request.getHeader(headerStr) ;
        if(authorization == null){
            return null ;
        }
        //base64 解码
        String authText = "" ;
        //base64解码
        Base64.Decoder decoder = Base64.getDecoder();
        try{
            authText = new String(decoder.decode(authorization), "UTF-8");
        }catch(Exception e){
            return null ;
        }

        //localhost#admin&1948556024@qq.com  qq#openId  wechat#unionId
        if(!authText.matches("^\\w+#{1}\\w+&?(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)?$")){
            return null ;
        }

        String registerMethod = authText.split("#")[0] ;
        if(registerMethod.equals("localhost")){
            //获取name和email，并从数据库查找
            String authContext = authText.split("#")[1] ;
            //admin&1948556024@qq.com
            if(!authContext.matches("^\\w+&{1}\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
                return null ;
            }
            String name = authContext.split("&")[0] ;
            String email = authContext.split("&")[1] ;
            //通过name和email查找用户
            User user = userService.login(name, email);
            return user ;
        }else if(registerMethod.equals("qq")){
            String openId = authText.split("#")[1] ;
            if(!openId.matches("^\\w+$")){
                return null ;
            }
            //通过openID查找用户
            User UserByOpenId = userService.findByOpenId(openId);
            return UserByOpenId ;

        }else if(registerMethod.equals("wechat")){
            String unionId = authText.split("#")[1] ;
            if(!unionId.matches("^\\w+$")){
                return null ;
            }
            //通过unionId查找用户
            User userByUnionId = userService.findByUnionId(unionId);
            return userByUnionId ;
        }else{
            return null ;
        }

    }


    private void returnJson(HttpServletResponse response, String result)  {
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
