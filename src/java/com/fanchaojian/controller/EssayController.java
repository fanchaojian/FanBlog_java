package com.fanchaojian.controller;

import com.fanchaojian.domain.Essay;
import com.fanchaojian.service.IEssayService;
import com.fanchaojian.utils.JsonResult;
import com.fanchaojian.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 心情随笔控制器
 * @author fanchaojian
 * @date 2020-9-22 - 18:13
 */
@Controller
@RestController
@RequestMapping("essay")
public class EssayController {
    @Autowired
    private IEssayService essayService ;

    /*查找所有*/        //----blog
    @GetMapping("")
    public JsonResult findAll(){
        return ResultUtils.success(essayService.findAll()) ;
    }

    /*删除essay*/       //----admin
    @PostMapping("delete")
    public JsonResult deleteEssay(Integer id){
        if(id == null){
            throw new RuntimeException("请指定参数id") ;
        }
        return ResultUtils.success(essayService.deleteEssay(id)) ;
    }

    /*添加随笔*/        //----admin
    @PostMapping("save")
    public JsonResult saveEssay(String content){
        Essay essay = new Essay();
        essay.setContent(content);
        essay.setCreateDate(new Date());
        return ResultUtils.success(essayService.addEssay(essay)) ;
    }
}
