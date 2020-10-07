package com.fanchaojian.service.impl;

import com.fanchaojian.dao.ILabelDao;
import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.Label;
import com.fanchaojian.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 15:53
 */
@Service
public class LabelServiceImpl implements ILabelService {

    @Autowired
    private ILabelDao labelDao ;

    @Override
    public Label findByName(String labelName) {
        return labelDao.findByName(labelName);
    }

    @Override
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    @Override
    public Label updateLabel(Label label) {
        return  labelDao.updateLabel(label);
    }

    @Override
    public Label addLabel(Label label) {
        //先通过名称查找
        Label byName = labelDao.findByName(label.getLabelName());
        if(byName==null){
            labelDao.addLabel(label);
            return label ;
        }else{
            return null ;
        }
    }
}
