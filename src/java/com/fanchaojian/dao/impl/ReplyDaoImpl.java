package com.fanchaojian.dao.impl;

import com.fanchaojian.dao.IReplyDao;
import com.fanchaojian.domain.Reply;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author fanchaojian
 * @date 2020-9-27 - 13:34
 */
@Repository
public class ReplyDaoImpl implements IReplyDao {
    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public Reply addReply(Reply reply) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(reply) ;
            return reply;	
        } catch (Exception e) {
            System.out.println("回复失败，报错："+e.getMessage()) ;
            e.printStackTrace(); ; 
        	return null ; 
        }
        
    }
}
