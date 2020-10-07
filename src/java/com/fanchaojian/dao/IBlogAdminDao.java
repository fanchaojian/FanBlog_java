package com.fanchaojian.dao;

import com.fanchaojian.domain.BlogAdmin;
import com.fanchaojian.domain.InvokeResult;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 17:18
 */
public interface IBlogAdminDao {
    /*查找*/
    BlogAdmin findAdmin() ;

    /*修改基本信息*/
    BlogAdmin updateBlogAdmin(BlogAdmin blogAdmin) ;

    /*修改用户名和密码*/
    BlogAdmin updateLoginInfo(Integer adminId,String username,String password) ;

    /*登录逻辑，通过用户名和密码查找管理员*/
    BlogAdmin login(String username,String password) ;
}
