package com.fanchaojian.dao;

import com.fanchaojian.domain.Article;
import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.Label;
import com.sun.org.apache.xpath.internal.operations.Bool;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 15:16
 */
public interface IArticleDao {
    /*增加*/
    Boolean saveArticle(Article article) ;

    /*删除*/
    Boolean deleteArticle(int articleId) ;

    /*通过id查找*/
    Article findById(Integer articleId) ;

    /*模糊查找，支持分页*/
    List<Article> findByKeywords(String keywords,int pageSize,int pageCount) ;

    /*查找所有，支持分页*/
    List<ArrayList> findAll(int pageSize,int pageCount) ;

    /*修改*/
    Article updateArticle(Article article) ;

    /*通过标签名称查找所有的文章*/
    List<Article>  getArticleByLabel(Label label) ;

    /*通过年份分组查找文章*/
    Map<String,List<Article>> getArticleByYear() ;

    /*增加评论*/
    void doComment (int articleId) ;

    /*增减阅读数*/
    void doRead (int articleId) ;

    /*添加获赞数*/
    void doLike (int articleId) ;
}
