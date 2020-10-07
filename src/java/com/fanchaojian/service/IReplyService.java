package com.fanchaojian.service;

import com.fanchaojian.domain.Reply;

/**
 * @author fanchaojian
 * @date 2020-9-27 - 13:36
 */
public interface IReplyService {
    public Reply addReply(int articleID,int commentID,int targetID,int fromUid,int toUid,String replyType,String content) ;
}
