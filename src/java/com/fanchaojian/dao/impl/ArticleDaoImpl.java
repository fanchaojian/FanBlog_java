package com.fanchaojian.dao.impl;

import com.fanchaojian.dao.IArticleDao;
import com.fanchaojian.domain.Article;
import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.Label;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 15:23
 */
@Repository("articleDao")
public class ArticleDaoImpl implements IArticleDao {
    @Autowired
    private SessionFactory sessionFactory ;

    @Autowired
    private InvokeResult result ;

    /**
     * 保存博客文章
     * @param article
     * @return
     */
    @Override
    public Boolean saveArticle(Article article) {
        Session session = sessionFactory.getCurrentSession();
        session.save(article) ;
        return true ;
    }

    /**
     * 删除博客文章
     * @param
     * @return
     */
    @Override
    public Boolean deleteArticle(int articleId) {
        Session session = sessionFactory.getCurrentSession();
        Article article = session.get(Article.class, articleId);
        if(article == null){
            throw new RuntimeException("没有找到id为["+articleId+"]的记录。") ;
        }
        session.delete(article);
        return true ;

    }

    /**
     * 修改文章基本的信息
     * @param article
     * @return
     */
    @Override
    public Article updateArticle(Article article) {
        Session session = sessionFactory.getCurrentSession();
        session.update(article);
        return article ;
    }


    /**
     * 通过id查找
     * @param articleId
     * @return
     */
    @Override
    public Article findById(Integer articleId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Article.class,articleId) ;
    }

    /**
     * 模糊查询,分页
     * @param keywords
     * @return
     */
    @Override
    public List<Article> findByKeywords(String keywords,int pageSize,int pageCount) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select title,author,iconUrl,intro,createDate,commentCount,readCount,likeCount from Article where title like ? or content like ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,"%"+keywords+"%") ;
        query.setParameter(1,"%"+keywords+"%") ;

        int currentLine = (pageSize-1)*pageCount ;
        query.setFirstResult(currentLine) ;
        query.setMaxResults(pageCount) ;
        return query.list() ;
    }



    /**
     * 查找所有，分页
     * title,author,iconUrl,intro,createDate,commentCount,readCount,likeCount
     * @param pageSize
     * @param pageCount
     * @return
     */
    @Override
    public List<ArrayList> findAll(int pageSize,int pageCount) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Article order by createDate desc");
        int currentLine = (pageSize-1)*pageCount ;
        query.setFirstResult(currentLine) ;
        query.setMaxResults(pageCount) ;
        return query.list() ;
    }


    /**
     * 通过标签查找文章列表
     * title,author,createDate,commentCount,readCount,likeCount
     * @param label
     * @return
     */
    @Override
    public List<Article> getArticleByLabel(Label label) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Article where label = ?");
        query.setParameter(0,label) ;
        return query.list() ;
    }

    /**
     * 通过年份分组查找文章列表
     * @return
     */
    @Override
    public Map<String, List<Article>> getArticleByYear() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Article order by createDate asc");
        List<Article> list = query.list();

        Map<String,List<Article>> articleMap = new LinkedHashMap<String,List<Article>>() ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //遍历数据，根据createDate 判断再 放入map中
        for(Article article : list){
            if(article.getCreateDate() != null ){
                String formatDate = sdf.format(article.getCreateDate());
                String myDate = formatDate.substring(0,4) ;

                //判断map中是否有同名的key
                boolean hasKey = articleMap.containsKey(myDate);
                if(hasKey){
                    List<Article> articles = articleMap.get(myDate);
                    articles.add(article) ;
                }else{
                    ArrayList<Article> articles = new ArrayList<>();
                    articles.add(article) ;
                    articleMap.put(myDate,articles)  ;
                }
            }
        }

        return articleMap;
    }

    /**
     * 评论量+1
     * @param articleId
     */
    @Override
    public void doComment(int articleId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Article set commentCount = commentCount + ? where articleId = ?");
        query.setParameter(0,1) ;
        query.setParameter(1,articleId) ;
        query.executeUpdate() ;
    }

    /**
     * 阅读量+1
     * @param articleId
     */
    @Override
    public void doRead(int articleId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Article set readCount = readCount + ? where articleId = ?");
        query.setParameter(0,1) ;
        query.setParameter(1,articleId) ;
        query.executeUpdate() ;
    }

    /**
     * 获赞+1
     * @param articleId
     */
    @Override
    public void doLike(int articleId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Article set likeCount = likeCount + ? where articleId = ?");
        query.setParameter(0,1) ;
        query.setParameter(1,articleId) ;
        query.executeUpdate() ;
    }

}
