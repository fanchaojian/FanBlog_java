package com.fanchaojian.service;

import com.fanchaojian.domain.User;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-25 - 14:45
 */
public interface IUserService {
    /*登录*/
    User login(String name,String email) ;

    /*添加用户*/
    User addUser(User user) ;

    /*修改用户基本信息*/
    User modifyInfo(int userID,String email,String icon,String gender,int emailReply) ;

    /*通过id查找用户*/
    User findById(int id) ;

    /*通过openId查找用户*/
    User findByOpenId(String openId) ;

    /*通过unionId查找用户*/
    User findByUnionId(String unionId);

    /*查找所有用户*/
    List<User> getAll() ;

    /*删除用户，其实没有真正删除用户，只是将“是否启用字段设置为0”，但是以往的评论依然可见*/
    void deleteUser(int uid) ;

    /*移除用户，从数据库真正的移除，并删除其所有的评论和回复，以及所有与用户关联的回复*/
    Boolean dropUser(int userid) ;
}
