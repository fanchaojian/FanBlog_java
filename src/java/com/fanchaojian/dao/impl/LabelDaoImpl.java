package com.fanchaojian.dao.impl;

import com.fanchaojian.dao.ILabelDao;
import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.Label;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 15:18
 */
@Repository
public class LabelDaoImpl implements ILabelDao {
    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public Label findByName(String labelName) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Label where labelName = ?");
            query.setParameter(0,labelName) ;
            List<Label> list = query.list();
            if(!list.isEmpty() && list.size() != 0){
                return list.get(0) ;
            }else{
                return null ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        	return null ;
        }

    }

    /**
     * 查找所有标签
     * @return
     */
    @Override
    public List<Label> findAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Label> labes = session.createQuery("from Label").list();
            return labes;
        } catch (Exception e) {
        	return null ;
        }

    }

    /**
     * 修改标签名称和备注说明
     * @param la
     */
    @Override
    public Label updateLabel(Label la) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Label label = session.get(Label.class,la.getLabelId());
            label.setLabelName(la.getLabelName());
            label.setRemark(la.getRemark());
            return session.get(Label.class,la.getLabelId()) ;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null ;
        }
    }

    /**
     * 添加标签
     * @param label
     */
    @Override
    public Label addLabel(Label label) {
        try {
            //先通过名称查找标签，不能添加相同的标签
            Session session = sessionFactory.getCurrentSession();
            session.save(label) ;
            return label ;
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }

    }
}
