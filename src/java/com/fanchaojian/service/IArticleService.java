package com.fanchaojian.service;

import com.fanchaojian.domain.Article;
import com.fanchaojian.domain.InvokeResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 15:59
 */
public interface IArticleService {
    /*增加*/
    Article saveArticle(String labelName,Article article) ;

    /*删除*/
    Boolean deleteArticle(int articleId) ;

    /*通过id查找*/
    Article findById(Integer articleId) ;

    /*模糊查找*/
    List<Article> findByKeywords(String keywords,int pageSize,int pageCount) ;

    /*查找所有*/
    List<ArrayList> findAll(int pageSize,int pageCount) ;

    /*修改*/
    Article updateArticle(Article article) ;

    /*通过标签名称查找所有的文章*/
    List<Article>  getArticleByLabel(String labelname) ;

    /*如评论（comment）、点赞（like）、浏览数量(read)的增加*/
    void doDynamicInfo(int articleId,String type) ;

    /*通过年份分组查找文章*/
    Map<String,List<Article>> getArticleByYear() ;
}
