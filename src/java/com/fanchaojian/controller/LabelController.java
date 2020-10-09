package com.fanchaojian.controller;

import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.domain.Label;
import com.fanchaojian.service.ILabelService;
import com.fanchaojian.utils.JsonResult;
import com.fanchaojian.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-22 - 16:04
 */
@Controller
@RestController
@RequestMapping("label")
public class LabelController {
    @Autowired
    private ILabelService labelService ;

    /*通过名称查找标签*/        //----blog  已测试
    @GetMapping("/findByName")
    public JsonResult findByName(String labelName){
        return ResultUtils.success(labelService.findByName(labelName));
    }

    /*查找所有标签*/
    @GetMapping("")     //----blog
    public JsonResult findAll(){
        return ResultUtils.success(labelService.findAll()) ;
    }

    /*修改标签信息*/      //----admin 已测试
    @PostMapping("update/{labelid}")
    public JsonResult updateLabel(@PathVariable int labelid, String labelName,String remark){
        Label label = new Label();
        label.setLabelId(labelid);
        label.setLabelName(labelName);
        label.setRemark(remark);
        return ResultUtils.success(labelService.updateLabel(label)) ;
    }

    /*添加标签*/
    @PostMapping("save")      //----admin  已测试
    public JsonResult saveLabel(String labelName,String remark){
        Label label = new Label();
        label.setLabelName(labelName);
        label.setRemark(remark);
        label.setArticleCount(0);
        label.setCreateDate(new Date());
        return ResultUtils.success(labelService.addLabel(label));
    }
}
