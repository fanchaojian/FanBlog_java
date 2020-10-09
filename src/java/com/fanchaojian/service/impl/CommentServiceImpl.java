package com.fanchaojian.service.impl;

import com.fanchaojian.dao.IArticleDao;
import com.fanchaojian.dao.ICommentDao;
import com.fanchaojian.dao.IUserDao;
import com.fanchaojian.domain.Article;
import com.fanchaojian.domain.Comment;
import com.fanchaojian.domain.User;
import com.fanchaojian.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-25 - 17:54
 */
@Service("commentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentDao commentDao ;
    @Autowired
    private IUserDao userDao ;

    @Autowired
    private IArticleDao articleDao ;

    @Override
    public Comment findById(int commentID) {
        return commentDao.findById(commentID);
    }

    @Override
    public Comment addComment(String type, Integer articleId, int userId, String content) {
        if(type.equals("comment")){
            //通过articleId查找文章
            Article article = articleDao.findById(articleId);
            if(article == null){
                throw new RuntimeException("没有查找到指定articleId["+articleId+"]的文章，添加评论失败。") ;
            }

            article.setCommentCount(article.getCommentCount()+1);  //修改文章留言数
        }else if(! type.equals("comment") && ! type.equals("guestbook")){
            throw new RuntimeException("没有可选的type，请指定评论类型[comment,guestbook]") ;
        }

        //封装为comment对象
        Comment comment = new Comment();
        comment.setType(type);
        comment.setArticleId(articleId);
        comment.setContent(content);
        comment.setCreateDate(new Date());

        //通过id查询用户
        User user = userDao.findById(userId);
        if(user != null){
            comment.setUser(user);
            commentDao.addComment(comment) ;
            return comment ;
        }else{
            throw new RuntimeException("没有查找到指定userId["+userId+"]的用户，添加评论失败。") ;
        }
    }

    @Override
    public List<Comment> findByArticle(int articleId) {
        return commentDao.findByArticle(articleId);
    }

    @Override
    public Boolean deleteComment(int commentdID) {
        Comment comment = commentDao.findById(commentdID);
        if(comment != null){
            commentDao.deleteComment(comment);
            return true;
        }else{
            throw new RuntimeException("没有指定id的评论，删除失败。") ;
        }

    }
}
