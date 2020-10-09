package com.fanchaojian.dao.impl;

import com.fanchaojian.dao.IUserDao;
import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-24 - 18:09
 */
@Repository("userDao")
public class UserDaoImpl implements IUserDao {
    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public User login(String name, String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where name = ? and email = ? and registerMethod = ?");
        query.setParameter(0,name) ;
        query.setParameter(1,email) ;
        query.setParameter(2,"localhost") ;
        List<User> list = query.list();
        if(!list.isEmpty() && list.size() != 0){
            return list.get(0) ;
        }else{
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user ;
    }


    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email = ? and registerMethod = ?");
        query.setParameter(0,email) ;
        query.setParameter(1,"localhost") ;
        List<User> list = query.list();
        if(!list.isEmpty() && list.size() != 0){
            return list.get(0) ;
        }else{
            return null ;
        }
    }

    @Override
    public User findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where name = ? and registerMethod = ?");
        query.setParameter(0,name) ;
        query.setParameter(1,"localhost") ;
        List<User> list = query.list();
        if(!list.isEmpty() && list.size() != 0){
            return list.get(0) ;
        }else{
            return null;
        }
    }

    @Override
    public User findByOpendId(String opendID) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where openId = ? and registerMethod = ?");
        query.setParameter(0,opendID) ;
        query.setParameter(1,"qq") ;
        List<User> list = query.list();
        if(!list.isEmpty() && list.size()!=0){
            return list.get(0) ;
        }else{
            System.out.println("没有找到opendID为："+opendID+"的用户") ;
            return null ;
        }
    }

    @Override
    public User findByUnionId(String unionID) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where unionId = ? and registerMethod = ?");
        query.setParameter(0,unionID) ;
        query.setParameter(1,"wechat") ;
        List<User> list = query.list();
        if(!list.isEmpty() && list.size() != 0){
            return list.get(0) ;
        }else{
            return null ;
        }
    }

    @Override
    public User findById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(User.class, id);
        } catch (Exception e) {
            System.out.println("通过id查找用户失败") ;
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list() ;
    }

    @Override
    public void deleteUser(int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update User set isInuse = ? where userId = ?");
        query.setParameter(0,0) ;
        query.setParameter(1,uid) ;
    }

    @Override
    public void dropUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }
}
