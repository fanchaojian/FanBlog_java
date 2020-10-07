package com.fanchaojian.controller;

import com.fanchaojian.domain.Comment;
import com.fanchaojian.service.ICommentService;
import com.fanchaojian.utils.JsonResult;
import com.fanchaojian.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fanchaojian
 * @date 2020-9-25 - 18:03
 */
@Controller
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private ICommentService commentService ;

    /*通过文章id查找所有的评论,以及评论回复*/        //----blog
    @GetMapping("")
    public JsonResult findByArticle(int articleId) {
         return ResultUtils.success(commentService.findByArticle(articleId)) ;
    }



    /*通过ID查找评论,及评论的回复*/     //----blog
    @GetMapping("{commentId}")
    public JsonResult findById(@PathVariable int commentId){
        return ResultUtils.success(commentService.findById(commentId)) ;
    }



    /**              //-----blog
     * 添加评论
     * @param type 评论类型，文章评论：comment,留言：gurstbook
     * @param articleId 文章的id
     * @param userId    评论用户的id
     * @param content   文章内容
     * @return
     */
    @PostMapping("addComment")
    public JsonResult addComment(String type, Integer articleId, int userId, String content) {
        return ResultUtils.success(commentService.addComment(type,articleId,userId,content)) ;
    }
}
