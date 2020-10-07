package com.fanchaojian.service;

import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.Label;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 15:52
 */
public interface ILabelService {
    /*听过名称查找标签*/
    Label findByName(String labelName) ;

    /*查找所有标签*/
    List<Label> findAll() ;

    /*修改标签信息*/
    Label updateLabel(Label label) ;

    /*添加标签*/
    Label addLabel(Label label) ;
}
