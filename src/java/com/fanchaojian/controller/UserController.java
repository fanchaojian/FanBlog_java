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

    /*查找所有用户*/      //----admin
    @GetMapping("getAll")
    public JsonResult getAll() {
        return ResultUtils.success(userService.getAll())  ;
    }

    /*添加用户*/    //----blog
    @PostMapping("add")
    public JsonResult addUser(User user){
        return ResultUtils.success(userService.addUser(user)) ;
    }

    /*通过邮箱查找用户*/    //----blog
    @GetMapping("email")
    public JsonResult findByEmail(String email) {
        return ResultUtils.success(userService.findByEmail(email)) ;
    }

    /*通过openId查找用户信息，QQ接入用户*/       //----blog
    @GetMapping("opendID/{opendID}")
    public JsonResult findByOpendId(@PathVariable String opendID){
        return ResultUtils.success(userService.findByOpendId(opendID)) ;
    }

    /*通过unionId查找用户用户信息，wechat接入用户*/        //----blog
    @GetMapping("unionID/{unionID}")
    public JsonResult findByUnionId(@PathVariable String unionID) {
        return ResultUtils.success(userService.findByUnionId(unionID)) ;
    }

    /*通过id查找用户*/        //----blog
    @GetMapping("id/{id}")
    public JsonResult findById(@PathVariable int id) {
        return ResultUtils.success(userService.findById(id)) ;
    }

    /*删除用户，其实没有真正删除用户，只是将“是否启用字段设置为0”，但是以往的评论依然可见*/     //----admin
    @PostMapping("delete/{uid}")
    public JsonResult deleteUser(@PathVariable int uid) {
        userService.deleteUser(uid);
        return ResultUtils.success() ;
     }

    /*通过用户查找回复我的消息*/
}
