package com.fanchaojian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 回复表
 * @author fanchaojian
 * @date 2020-9-24 - 14:55
 */
@Entity
@Table(name="reply")
public class Reply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private Integer targetId ;      //可能是对评论的回复，也可能是对回复的回复，取值是commentId,replyId    ---->    消息回复
    private Integer articleId ;     //文章的id，这儿不做外键关联                                        ---->   消息回复功能
    private String replyType ;      /*回复类型，有两种情况。1：对评论的回复（comment）。2：对回复的回复（reply）*/
    @Column(columnDefinition = "varchar(400) default '' ")
    private String content ;        /*回复内容*/
    private Date createDate ;       /*回复时间*/

    /*评论id,与评论表是多对一的关系*/
    @JsonIgnore

    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn(name="commentId")
    private Comment comment ;

    /*回复用户，与user是多对一关系，*/
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="fromUid")
    private User fromUser ;

    /*目标用户，与User是多对一关系，表示是对评论的回复*/
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="toUid")
    private User toUser ;

    public Reply() {
    }

    public Reply(Integer targetId, Integer articleId, String replyType, String content, Date createDate, Comment comment, User fromUser, User toUser) {
        this.targetId = targetId;
        this.articleId = articleId;
        this.replyType = replyType;
        this.content = content;
        this.createDate = createDate;
        this.comment = comment;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}
