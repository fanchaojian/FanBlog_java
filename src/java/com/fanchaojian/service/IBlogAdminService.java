package com.fanchaojian.service;

import com.fanchaojian.domain.BlogAdmin;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 17:22
 */
public interface IBlogAdminService {
    /*查找*/
    BlogAdmin findAdmin() ;

    /*修改*/
    BlogAdmin updateBlogAdmin(BlogAdmin blogAdmin) ;

    /*修改用户名和密码*/
    BlogAdmin updateLoginInfo(Integer adminId,String username,String password) ;

    /*登录逻辑，通过用户名和密码查找管理员*/
    BlogAdmin login(String username,String password) ;
}
