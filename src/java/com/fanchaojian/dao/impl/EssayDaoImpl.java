package com.fanchaojian.dao.impl;

import com.fanchaojian.dao.IEssayDao;
import com.fanchaojian.domain.Essay;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 18:02
 */
@Repository("essayDao")
public class EssayDaoImpl implements IEssayDao {
    @Autowired
    private SessionFactory sessionFactory ;


    /**
     * 添加随笔
     * @param essay
     * @return
     */
    @Override
    public Essay addEssay(Essay essay) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(essay);
            return essay ;
        } catch (Exception e) {
            System.out.println("保存心情随笔失败") ;
        	e.printStackTrace();

        	return null ;
        }
        
    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    @Override
    public Essay findById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Essay essay = session.get(Essay.class, id);
            return essay;
        } catch (Exception e) {
            e.printStackTrace();
        	return null ;
        }

    }

    /**
     * 查找所有
     * @return
     */
    @Override
    public List<Essay> findAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Essay order by essayId desc");
            return query.list();
        } catch (Exception e) {
        	return null ;
        }

    }


    /**
     * 删除
     * @param essay
     * @return
     */
    @Override
    public Boolean deleteEssay(Essay essay) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(essay);
        return true;
    }
}
