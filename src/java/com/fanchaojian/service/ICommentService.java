package com.fanchaojian.service;

import com.fanchaojian.domain.Comment;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-25 - 17:48
 */
public interface ICommentService {
    /*通过id查找评论*/
    Comment findById(int commentID) ;

    /*添加评论*/
    Comment addComment(String type,Integer articleId,int userId,String content) ;


    /*通过文章id查找所有的评论,以及评论回复*/
    List<Comment> findByArticle(int articleId) ;

    Boolean deleteComment(int commentdID) ;
}
