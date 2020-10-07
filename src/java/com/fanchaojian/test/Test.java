package com.fanchaojian.test;

import com.fanchaojian.domain.Article;
import com.fanchaojian.domain.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 17:42
 */
public class Test {
    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession() ;
        Transaction tx = session.beginTransaction();

        Article article = session.get(Article.class, 1);
        System.out.println(article) ;
        tx.commit();
        session.close();

        //进行通讯测试
    }
    
}
