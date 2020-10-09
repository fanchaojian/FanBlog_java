package com.fanchaojian.dao;

import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.User;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-24 - 17:06
 */
public interface IUserDao {
    /*登录*/
    User login(String name,String email) ;

    /*添加用户*/
    User addUser(User user) ;


    /*通过邮箱查找用户*/
    User findByEmail(String email) ;

    /*通过name查找用户*/
    User findByName(String name) ;

    /*通过openId查找用户信息，QQ接入用户*/
    User findByOpendId(String opendID) ;

    /*通过unionId查找用户用户信息，wechat接入用户*/
    User findByUnionId(String unionID) ;

    /*通过id查找用户*/
    User findById(int id) ;

    /*查找所有用户*/
    List<User> getAll() ;

    /*删除用户，其实没有真正删除用户，只是将“是否启用字段设置为0”，但是以往的评论依然可见*/
    void deleteUser(int uid) ;

    /*移除用户*/
    void dropUser(User user) ;
}
