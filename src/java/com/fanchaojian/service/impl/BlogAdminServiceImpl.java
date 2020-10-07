package com.fanchaojian.service.impl;

import com.fanchaojian.dao.IBlogAdminDao;
import com.fanchaojian.domain.BlogAdmin;
import com.fanchaojian.service.IBlogAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 17:23
 */
@Service("blogAdminService")
public class BlogAdminServiceImpl implements IBlogAdminService {
    @Autowired
    private IBlogAdminDao blogAdminDao ;

    @Override
    public BlogAdmin findAdmin() {
        return blogAdminDao.findAdmin();
    }

    @Override
    public BlogAdmin updateBlogAdmin(BlogAdmin blogAdmin) {

        return blogAdminDao.updateBlogAdmin(blogAdmin) ;
    }

    @Override
    public BlogAdmin updateLoginInfo(Integer adminId,String username,String password) {
        return blogAdminDao.updateLoginInfo(adminId,username,password);
    }

    @Override
    public BlogAdmin login(String username, String password) {
        return blogAdminDao.login(username,password);
    }
}
