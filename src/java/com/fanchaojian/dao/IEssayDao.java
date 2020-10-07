package com.fanchaojian.dao;

import com.fanchaojian.domain.Essay;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 17:58
 */
public interface IEssayDao {
    /*添加随笔*/
    Essay addEssay(Essay essay) ;

    /*通过id查找*/
    Essay findById(int id) ;

    /*查找所有*/
    List<Essay> findAll() ;

    /*删除essay*/
    Boolean deleteEssay(Essay essay) ;
}
