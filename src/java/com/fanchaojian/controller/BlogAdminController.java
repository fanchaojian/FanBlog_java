package com.fanchaojian.controller;

import com.fanchaojian.domain.BlogAdmin;
import com.fanchaojian.service.IBlogAdminService;
import com.fanchaojian.utils.JsonResult;
import com.fanchaojian.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 17:00
 */
@Controller
@RestController
@RequestMapping("blogAdmin")
public class BlogAdminController {
    @Autowired
    private IBlogAdminService blogAdminService ;

    /*查找管理员用户*/     //----blog  已测试
    @GetMapping("")
    public JsonResult findAdmin(){
        return ResultUtils.success(blogAdminService.findAdmin()) ;
    }



    /**
     * 修改基本信息      //----admin  已测试
     * @param blogAdmin
     *      adminId:
     *      "name":
            "job":
            "email":
            "place":
            "hobby":
            "motto":
            "introUrl":
            "gender":
            "iconUrl":
            "qqQrcode":
            "wechatQrcode":
            "weboQrcode":
            "gghQrcode":
            "githubUrl":
     * @return
     */
    @PostMapping("Admin/updateBaseInfo")
    public JsonResult updateBlogAdmin(BlogAdmin blogAdmin){
        return ResultUtils.success(blogAdminService.updateBlogAdmin(blogAdmin)) ;
    }

    /*修改用户名和密码*/    //----admin  已测试
    @PostMapping("updateLoginInfo")
    public JsonResult updateLoginInfo(Integer adminId,String username,String password){
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            return ResultUtils.error(101,"用户名或密码不能为空") ;
        }

        return ResultUtils.success(blogAdminService.updateLoginInfo(adminId,username.trim(),password.trim()));
    }

    /*登录逻辑*/   //---all  已测试
    @PostMapping("login")
    public JsonResult login(String username,String password){
        BlogAdmin admin = blogAdminService.login(username, password);
        if(admin == null){
            return ResultUtils.error(101,"登录失败，检查用户名或密码") ;
        }

        return ResultUtils.success(admin) ;
    }
}
