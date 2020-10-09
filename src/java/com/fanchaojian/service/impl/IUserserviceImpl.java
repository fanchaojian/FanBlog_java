package com.fanchaojian.service.impl;

import com.fanchaojian.dao.IUserDao;
import com.fanchaojian.domain.User;
import com.fanchaojian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-25 - 14:46
 */
@Service
public class IUserserviceImpl implements IUserService {
    @Autowired
    private IUserDao userDao ;

    @Override
    public User login(String name, String email) {
        return userDao.login(name,email);
    }

    @Override
    public User addUser(User user) {
        if(user.getRegisterMethod() == null){
            throw new RuntimeException("请指定注册方式：registerMethod") ;
        }else if(user.getRegisterMethod().equals("localhost")){
            //判断数据库中是否已经存在相同name或email的用户
            User userByName = userDao.findByName(user.getName());
            if(userByName != null){
                throw new RuntimeException("已经存在昵称为["+user.getName()+"]的账户。") ;
            }
            User userByEmail = userDao.findByEmail(user.getEmail());
            if(userByEmail != null){
                throw new RuntimeException("此邮箱["+user.getEmail()+"]已经注册，请登录。") ;
            }

            user.setOpenId("");
            user.setUnionId("");
            user.setIsInuse(1);
            return userDao.addUser(user);
        }else if(user.getRegisterMethod().equals("qq")){
            if(user.getOpenId() != null && user.getOpenId().trim().length() != 0){
                //先查找数据库中是否已经存在此qq登录的账户
                User userByOpendId = userDao.findByOpendId(user.getOpenId());
                if(userByOpendId != null){
                    return userByOpendId ;
                }else{
                    user.setUnionId("");
                    user.setEmailReply(0);
                    user.setEmail("");
                    user.setIsInuse(1);
                    return userDao.addUser(user);
                }
            }else{
                throw new RuntimeException("请指定openId。") ;
            }

        }else if(user.getRegisterMethod().equals("wechat")){
            if(user.getUnionId() != null && user.getUnionId().trim().length() != 0){
                //先查找数据库中是否已经存在此wechat的账户
                User userByUnionId = userDao.findByUnionId(user.getUnionId());
                if(userByUnionId != null){
                    return userByUnionId ;
                }else{
                    user.setOpenId("");
                    user.setEmailReply(0);
                    user.setEmail("");
                    user.setIsInuse(1);
                    return userDao.addUser(user);
                }
            }else{
                throw new RuntimeException("请指定unionId。") ;
            }

        }else{
            throw new RuntimeException("请指定注册方式：localhost、qq、wechat") ;
        }

    }

    @Override
    public User modifyInfo(int userID, String email, String icon, String gender, int emailReply) {
        //查找账户
        User userById = userDao.findById(userID);
        if(userById != null){
            userById.setEmail(email);
            userById.setIcon(icon);
            userById.setGender(gender);
            userById.setEmailReply(emailReply);
            return userById ;
        }else{
            throw new RuntimeException("没有找到指定userID["+userID+"]的账户。") ;
        }

    }


    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByOpenId(String openId) {
        return userDao.findByOpendId(openId);
    }

    @Override
    public User findByUnionId(String unionId) {
        return userDao.findByUnionId(unionId);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void deleteUser(int uid) {
        userDao.deleteUser(uid);
    }

    @Override
    public Boolean dropUser(int userid) {
        User user = userDao.findById(userid);
        if(user != null){
            userDao.dropUser(user);
            return true ;
        }else{
            throw new RuntimeException("没有找到指定userID["+userid+"]的用户，删除失败") ;
        }
    }
}
