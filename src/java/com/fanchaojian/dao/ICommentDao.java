package com.fanchaojian.dao;

import com.fanchaojian.domain.Comment;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-24 - 15:42
 */
public interface ICommentDao {
    /*添加评论*/
    Comment addComment(Comment comment) ;


    /*通过文章id查找所有的评论,以及评论回复*/
    List<Comment> findByArticle(int articleId) ;

    /*通过id查找评论*/
    Comment findById(int commentID) ;

    void deleteComment(Comment comment) ;

}
