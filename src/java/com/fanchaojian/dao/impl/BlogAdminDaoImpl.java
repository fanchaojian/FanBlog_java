package com.fanchaojian.dao.impl;

import com.fanchaojian.dao.IBlogAdminDao;
import com.fanchaojian.domain.BlogAdmin;
import com.fanchaojian.domain.InvokeResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 17:20
 */
@Repository("blogAdminDao")
public class BlogAdminDaoImpl implements IBlogAdminDao {
    @Autowired
    private SessionFactory sessionFactory  ;

    /**
     * 查找系统管理员，默认为第一条数据
     * @return
     */
    @Override
    public BlogAdmin findAdmin() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from BlogAdmin order by preOrder asc");

        List<BlogAdmin> list = query.list();
        if(!list.isEmpty() && list.size() != 0){
            return list.get(0) ;
        }else{
            return null ;
        }
    }

    /**
     * 修改基本信息
     * @param blogAdmin
     * @return
     */
    @Override
    public BlogAdmin updateBlogAdmin(BlogAdmin blogAdmin) {

            Session session = sessionFactory.getCurrentSession();
            BlogAdmin admin = session.get(BlogAdmin.class, blogAdmin.getAdminId());
            if(admin == null){
                throw new RuntimeException("没有找到id为["+blogAdmin.getAdminId()+"]的记录，修改记录失败") ;
            }
            admin.setName(blogAdmin.getName());
            admin.setJob(blogAdmin.getJob());
            admin.setEmail(blogAdmin.getEmail());
            admin.setPlace(blogAdmin.getPlace());
            admin.setHobby(blogAdmin.getHobby());
            admin.setMotto(blogAdmin.getMotto());
            admin.setGender(blogAdmin.getGender());
            admin.setIconUrl(blogAdmin.getIconUrl());
            admin.setIntroUrl(blogAdmin.getIntroUrl());
            admin.setQqQrcode(blogAdmin.getQqQrcode());
            admin.setWechatQrcode(blogAdmin.getWechatQrcode());
            admin.setWeboQrcode(blogAdmin.getWeboQrcode());
            admin.setGghQrcode(blogAdmin.getGghQrcode());
            admin.setGithubUrl(blogAdmin.getGithubUrl());

            session.update(admin);
            return admin;

    }

    /**
     * 修改用户名和密码
     * @return
     */
    @Override
    public BlogAdmin updateLoginInfo(Integer adminId,String username,String password) {
        System.out.println("adminid:"+adminId) ;
        if(adminId == null){
            throw new RuntimeException("the adminID is Null") ;
        }
        Session session = sessionFactory.getCurrentSession();
        BlogAdmin admin = session.get(BlogAdmin.class, adminId);
        if(admin == null){
            throw new RuntimeException("没有找到id为["+adminId+"]的记录，更改用户名或密码失败") ;
        }
        admin.setUsername(username);
        admin.setPassword(password);
        session.update(admin);

        return admin ;
    }

    @Override
    public BlogAdmin login(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from BlogAdmin where username=? and password = ?");
        query.setParameter(0,username) ;
        query.setParameter(1,password) ;
        List<BlogAdmin> list = query.list();
        if(!list.isEmpty() && list.size() != 0){
            return list.get(0) ;
        }
        return null;
    }
}
