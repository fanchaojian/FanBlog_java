package com.fanchaojian.service.impl;

import com.fanchaojian.dao.IArticleDao;
import com.fanchaojian.dao.ICommentDao;
import com.fanchaojian.dao.IReplyDao;
import com.fanchaojian.dao.IUserDao;
import com.fanchaojian.domain.Comment;
import com.fanchaojian.domain.Reply;
import com.fanchaojian.domain.User;
import com.fanchaojian.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author fanchaojian
 * @date 2020-9-27 - 13:41
 */
@Service
public class ReplyServiceImpl implements IReplyService {
    @Autowired
    private IReplyDao replyDao ;
    @Autowired
    private ICommentDao commentDao ;
    @Autowired
    private IUserDao userDao ;
    @Autowired
    private IArticleDao articleDao ;

    @Override
    public Reply addReply(int articleID, int commentID, int targetID, int fromUid, int toUid, String replyType, String content) {
        try {
            //查找父评论
            Comment comment = commentDao.findById(commentID);
            //查找form user ;
            User fromUser = userDao.findById(fromUid);
            //查找to user
            User toUser = userDao.findById(toUid);

            //封装Reply;
            Reply reply = new Reply();
            reply.setArticleId(articleID);
            reply.setComment(comment);
            reply.setTargetId(targetID);
            reply.setFromUser(fromUser);
            reply.setToUser(toUser);
            reply.setReplyType(replyType);
            reply.setContent(content);
            reply.setCreateDate(new Date());


            Reply re = replyDao.addReply(reply);
            articleDao.doComment(articleID);

            return re;
        } catch (Exception e) {
            e.printStackTrace();
        	return null ;
        }
    }
}
