package com.fanchaojian.controller;

import com.fanchaojian.domain.User;
import com.fanchaojian.service.IUserService;
import com.fanchaojian.utils.JsonResult;
import com.fanchaojian.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-25 - 14:51
 */
@Controller
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService ;

    /*登录*/      //----blog  已测试
    @PostMapping("login")
    public JsonResult login(String name,String email){
        return ResultUtils.success(userService.login(name,email)) ;
    }

    /*修改用户基本信息*/
    @PostMapping("modifyInfo")    //----blogUser
    public JsonResult modifyInfo(int userID,String email,String icon,String gender,int emailReply){
        return ResultUtils.success(userService.modifyInfo(userID,email,icon,gender,emailReply)) ;
    }

    /*查找所有用户*/      //----admin
    @GetMapping("getAll")
    public JsonResult getAll() {
        return ResultUtils.success(userService.getAll())  ;
    }

    /**
     *注册或查找用户    //----blog        已测试
     * 如果是localhost注册将只进行保存操作，如果是第三方登录将执行保存或查找的操作
     * @param user
     *          registerMethod  localhost，qq,wechat
     *          name
     *          email       允许修改
     *          openId
     *          unionId
     *          icon        允许修改
     *          gender      允许修改
     *          emailReply  允许修改
     * @return
     */
    @PostMapping("addOrFind")
    public JsonResult addOrFind(User user){
        return ResultUtils.success(userService.addUser(user)) ;
    }

    /*通过id查找用户*/        //----blog
    @GetMapping("findById")
    public JsonResult findById(int userID) {
        return ResultUtils.success(userService.findById(userID)) ;
    }

    //此功能暂时不启用
    /*删除用户，其实没有真正删除用户，只是将“是否启用字段设置为0”，但是以往的评论依然可见*/     //----admin
    @PostMapping("delete")
    public JsonResult deleteUser(int uid) {
        userService.deleteUser(uid);
        return ResultUtils.success() ;
     }

    /*销毁账户并删除与之关联的所有评论与回复*/
    @PostMapping("drop")    //----admin  已测试
    public JsonResult dropUser(int userID){
        return ResultUtils.success(userService.dropUser(userID)) ;
    }
}
