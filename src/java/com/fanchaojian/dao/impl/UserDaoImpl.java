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
    public User addUser(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(user);
            return user ;

        } catch (Exception e) {
            System.out.println("添加用户失败") ;
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public User findByEmail(String email) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from User where email = ?");

            query.setParameter(0,email) ;
            List<User> list = query.list();
            if(!list.isEmpty() && list.size() != 0){
                return list.get(0) ;
            }else{
                return null ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User findByOpendId(String opendID) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from User where openId = ?");
            query.setParameter(0,opendID) ;
            List<User> list = query.list();
            if(!list.isEmpty() && list.size()!=0){
                return list.get(0) ;
            }else{
                System.out.println("没有找到opendID为："+opendID+"的用户") ;
                return null ;
            }

        } catch (Exception e) {
            System.out.println("通过opendId查找用户失败") ; 
            e.printStackTrace();
        	return null ;
        }

    }

    @Override
    public User findByUnionId(String unionID) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from User where unionId = ?");
            query.setParameter(0,unionID) ;
            List<User> list = query.list();
            if(!list.isEmpty() && list.size() != 0){
                return list.get(0) ;
            }else{
                return null ;
            }
        } catch (Exception e) {
            return null;
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
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from User");
            return query.list() ;
        } catch (Exception e) {
            System.out.println("查找所有用户失败") ; 
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteUser(int uid) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("update User set isInuse = ? where userId = ?");
            query.setParameter(0,0) ;
            query.setParameter(1,uid) ;
        } catch (Exception e) {
            System.out.println("删除用户失败") ; 
        	e.printStackTrace();
        }
    }
}
