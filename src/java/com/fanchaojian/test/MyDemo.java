package com.fanchaojian.test;

import com.fanchaojian.dao.IArticleDao;
import com.fanchaojian.domain.Article;
import com.fanchaojian.domain.BlogAdmin;
import com.fanchaojian.domain.Essay;
import com.fanchaojian.domain.User;
import com.fanchaojian.service.IArticleService;
import com.fanchaojian.service.IBlogAdminService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 16:25
 */
public class MyDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();*/

        /*Query query = session.createQuery("update Article set likeCount = likeCount + ? where articleId = ?");
        query.setParameter(0,1) ;
        query.setParameter(1,2) ;
        query.executeUpdate() ;*/

        /*Article article = session.get(Article.class, 2);
        article.setCreateDate(new Date());

        session.save(article);*/

        /*Query query = session.createQuery("from Article order by createDate asc");
        List<Article> list = query.list();

        Map<String,List<Article>> articleMap = new TreeMap<String,List<Article>>() ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //遍历数据，根据createDate 判断再 放入map中
        for(Article article : list){
            if(article.getCreateDate() != null){
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


        //遍历map,输出结果
        System.out.println("========map结果==========") ;
        Set<String> keys = articleMap.keySet();
        for(String key:keys){
            System.out.println(key) ;
            List<Article> articles = articleMap.get(key);
            for(Article article:articles){
                System.out.println(article.toString()) ; 
            }
        }
        System.out.println("====================================") ;

        */

       /* Essay essay = new Essay();
        essay.setContent("xixixi");
        essay.setCreateDate(new Date());
        session.save(essay);
        
        System.out.println(essay.toString()) ;

        tx.commit();
        session.close();*/

        /*String auth = "localhost#fanchaojian&1948556024@qq.com" ;
        Base64.Encoder encoder = Base64.getEncoder() ;
        byte[] textByte = auth.getBytes("UTF-8") ;
        String accessLoginStr = encoder.encodeToString(textByte);
        System.out.println("编码后的字符串"+accessLoginStr) ;*/

        /*String auth = "qq#QQdsfjksdfisfn" ;
        Base64.Encoder encoder = Base64.getEncoder() ;
        byte[] textByte = auth.getBytes("UTF-8") ;
        String accessLoginStr = encoder.encodeToString(textByte);
        System.out.println("编码后的字符串"+accessLoginStr) ;*/

       /* String auth = "wechat#WECHATsdfskfshiosdj" ;
        Base64.Encoder encoder = Base64.getEncoder() ;
        byte[] textByte = auth.getBytes("UTF-8") ;
        String accessLoginStr = encoder.encodeToString(textByte);
        System.out.println("编码后的字符串"+accessLoginStr) ;*/
       
      /* String str = "admin&1948556024@qq.com" ;
       System.out.println(str.matches("^\\w+&{1}\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) ;*/


       /* String str = "WECHATsdfskfshiosdj1" ;
        System.out.println(str.matches("^\\w+$")) ;*/

        /*String str = " " ;
        System.out.println(str.isEmpty()) ;*/

        User user1 = new User();
        user1.setName("fanchaojian");

        User user2 = new User();
        user2.setName("fanchaojian");
        
        System.out.println(user1.hashCode() == user2.hashCode()) ;

    }
}
