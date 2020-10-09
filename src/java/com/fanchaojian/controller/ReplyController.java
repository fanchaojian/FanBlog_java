package com.fanchaojian.controller;

import com.fanchaojian.dao.IReplyDao;
import com.fanchaojian.domain.Reply;
import com.fanchaojian.service.IReplyService;
import com.fanchaojian.utils.JsonResult;
import com.fanchaojian.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanchaojian
 * @date 2020-9-27 - 14:02
 */

@Controller
@RestController
@RequestMapping("reply")
public class ReplyController {
    @Autowired
    private IReplyService replyService ;

    /**                    //----blog
     * 添加回复
     * @param articleID
     * @param commentID
     * @param targetID  (replyId & commentId)	回复的目标，可能是对评论的回复也可能是回复的回复，取值根据“回复类型”来决定。
     * @param fromUid
     * @param toUid
     * @param replyType 1：对评论的回复comment。2：对回复的回复reply
     * @param content
     * @return
     */
    @PostMapping("baseUser/add")
    public JsonResult addReply(int articleID, int commentID, int targetID, int fromUid, int toUid, String replyType, String content){
        return ResultUtils.success(replyService.addReply(articleID,commentID,targetID,fromUid,toUid,replyType,content)) ;
    }
}
