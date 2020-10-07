package com.fanchaojian.service.impl;

import com.fanchaojian.dao.IEssayDao;
import com.fanchaojian.domain.Essay;
import com.fanchaojian.service.IEssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 18:09
 */
@Service
public class EssayServiceImpl implements IEssayService {
    @Autowired
    private IEssayDao essayDao ;

    @Override
    public List<Essay> findAll() {
        return essayDao.findAll();
    }

    @Override
    public Boolean deleteEssay(Integer id) {
        //通过id查找
        Essay essay = essayDao.findById(id);
        if(essay == null){
            throw new RuntimeException("没有指定id["+id+"]的记录，删除失败。") ;
        }
        return essayDao.deleteEssay(essay);
    }

    @Override
    public Essay addEssay(Essay essay) {
        return essayDao.addEssay(essay);
    }
}
