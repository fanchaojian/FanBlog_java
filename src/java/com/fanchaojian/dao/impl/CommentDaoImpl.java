package com.fanchaojian.dao.impl;

import com.fanchaojian.dao.ICommentDao;
import com.fanchaojian.domain.Comment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-25 - 17:43
 */
@Repository("commentDao")
public class CommentDaoImpl implements ICommentDao {
    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public Comment addComment(Comment comment) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(comment) ;
            return comment;
        } catch (Exception e) {
            System.out.println("评论失败") ; 
            e.printStackTrace();
        	return null ;
        }

    }

    @Override
    public List<Comment> findByArticle(int articleId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Comment where articleId = ?");
            query.setParameter(0,articleId) ;
            List<Comment> list = query.list();
            return list;	
        } catch (Exception e) {
            System.out.println("查询文章下的评论失败") ;
            e.printStackTrace();
        	return null ; 
        }
        
    }

    @Override
    public Comment findById(int commentID) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Comment comment = session.get(Comment.class, commentID);
            return comment;
        } catch (Exception e) {
            e.printStackTrace();
        	return null ;
        }

    }
}
