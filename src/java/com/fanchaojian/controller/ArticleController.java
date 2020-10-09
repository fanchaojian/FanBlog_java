package com.fanchaojian.controller;

import com.fanchaojian.domain.Article;
import com.fanchaojian.domain.BlogAdmin;
import com.fanchaojian.domain.InvokeResult;
import com.fanchaojian.service.IArticleService;
import com.fanchaojian.service.IBlogAdminService;
import com.fanchaojian.service.impl.ArticleServiceImpl;
import com.fanchaojian.utils.JsonResult;
import com.fanchaojian.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 16:02
 */
@Controller
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private IArticleService articleService ;

    /*查找所有文章*/     //----blog 已测试
    @GetMapping("")
    public JsonResult findAll(int pageSize,int pageCount){
        List<ArrayList> articles = articleService.findAll(pageSize, pageCount);
        return ResultUtils.success(articles);
    }

    /*通过标签名查找所有的文章*/    //----blog  已测试
    @GetMapping("findByLabel")
    public JsonResult findArticlesByLable(String labelName){
        List<Article> articles = articleService.getArticleByLabel(labelName);
        return ResultUtils.success(articles) ;
    }

    /*保存博客文章*/      //----admin  已测试
    @PostMapping("Admin/save")
    public JsonResult saveArticle(String labelName,String title,String author,String iconUrl,String intro,String content,String reprintSite){
        /*封装article*/
        Article article = new Article(title,author,iconUrl,intro,content,new Date(),reprintSite,0,0,0);
        Article article1 = articleService.saveArticle(labelName, article);
        return ResultUtils.success(article1) ;
    }

    /*删除文章*/      //----admin  已测试
    @PostMapping("Admin/delete")
    public JsonResult deleteArticle(int articleId){
        System.out.println("文章id"+articleId) ;
        return ResultUtils.success(articleService.deleteArticle(articleId)) ;
    }


    /**
     * 修改文章基本信息     //----admin  已测试
     * articleId,title，author，iconUrl，intro，content，reprintSite，-----博客文章最基本的信息
     * @return
     * 必填字段：articleId
     */
    @PostMapping("Admin/update")
    public JsonResult updateArticle(Article article){
        return ResultUtils.success(articleService.updateArticle(article)) ;
    }


    /**
     * 修改文章动态字段：如评论数，浏览数，获赞数    //----blog  已测试
     * @param articleId
     * @param type
     * type:comment:评论数
     *      read：浏览量
     *      like：获赞数
     */
    @PostMapping("dynamicInfo")
    public JsonResult doDynamicInfo(int articleId,String type){
        System.out.println("执行点赞，浏览，评论逻辑"+articleId+"******"+type) ;
        articleService.doDynamicInfo(articleId,type);
        return ResultUtils.success() ;
    }


    /**
     * 获取年份已经对应的文章列表        //blog  --已测试
     * @return
     *
     *
     * 这儿返回的JSON数据有问题，序列化为JSON过后会会自动排布为KEY值从小到大排布，使用时需要转化为数组后进行重新排布使用
     */
    @GetMapping("byYear")
    public JsonResult findArticleByYear(){
        return ResultUtils.success(articleService.getArticleByYear()) ;
    }
}
