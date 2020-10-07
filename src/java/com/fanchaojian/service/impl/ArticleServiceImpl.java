package com.fanchaojian.service.impl;

import com.fanchaojian.dao.IArticleDao;
import com.fanchaojian.domain.Article;
import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.Label;
import com.fanchaojian.service.IArticleService;
import com.fanchaojian.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 15:59
 */
@Service("articleService")
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private InvokeResult invokeResult ;

    @Autowired
    private IArticleDao articleDao ;

    @Autowired
    private ILabelService labelService ;

    @Override
    public Article saveArticle(String labelName,Article article) {
        //查找标签
        Label label = labelService.findByName(labelName);
        if(label != null){
            article.setLabel(label);
            Boolean aBoolean = articleDao.saveArticle(article);
            label.setArticleCount(label.getArticleCount()+1);
            if(aBoolean){
                return article ;
            }
            return null ;
        }else{
            throw new RuntimeException("没有查找到名称为["+labelName+"]的标签") ;
        }
    }

    @Override
    public Boolean deleteArticle(int articleId) {
        //通过id查找文章

        return articleDao.deleteArticle(articleId);
    }

    @Override
    public Article findById(Integer articleId) {
        return articleDao.findById(articleId);
    }

    @Override
    public List<Article> findByKeywords(String keywords,int pageSize,int pageCount) {
        return articleDao.findByKeywords(keywords,pageSize,pageCount);
    }

    @Override
    public List<ArrayList> findAll(int pageSize,int pageCount) {
        return articleDao.findAll(pageSize,pageCount);
    }

    @Override
    public Article updateArticle(Article article) {
        Article byId = articleDao.findById(article.getArticleId());
        if(byId == null){
            throw new RuntimeException("没有找到id为["+article.getArticleId()+"]的记录，修改失败") ;
        }
        byId.setTitle(article.getTitle());
        byId.setAuthor(article.getAuthor());
        byId.setIconUrl(article.getIconUrl());
        byId.setIntro(article.getIntro());
        byId.setContent(article.getContent());
        byId.setReprintSite(article.getReprintSite());

        return articleDao.updateArticle(byId);
    }

    @Override
    public List<Article> getArticleByLabel(String labelname) {
        //通过标签名查找标签
        Label label = labelService.findByName(labelname);
        if(label != null){
            return articleDao.getArticleByLabel(label);
        }else{
            return null ;
        }
    }


    @Override
    public void doDynamicInfo(int articleId, String type) {
        Article article = articleDao.findById(articleId);
        if(article == null){
            throw new RuntimeException("没有找到id为["+articleId+"]的记录，更新动态字段失败") ;
        }
        if("comment".equals(type)){
            articleDao.doComment(articleId);
            System.out.println("执行评论") ; 
        }else if("read".equals(type)){
            articleDao.doRead(articleId);
            System.out.println("执行浏览") ; 
        }else if("like".equals(type)){
            System.out.println("执行点赞") ; 
            articleDao.doLike(articleId);
        }else {
            throw new RuntimeException("没有可选的类型["+type+"],可供选择类型comment，read，like") ;
        }
    }

    @Override
    public Map<String, List<Article>> getArticleByYear() {
        return articleDao.getArticleByYear();
    }

}
