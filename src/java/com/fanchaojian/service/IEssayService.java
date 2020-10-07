package com.fanchaojian.service;

import com.fanchaojian.domain.Essay;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 18:09
 */
public interface IEssayService {
    /*查找所有*/
    List<Essay> findAll() ;

    /*删除essay*/
    Boolean deleteEssay(Integer id) ;

    /*添加随笔*/
    Essay addEssay(Essay essay) ;
}
