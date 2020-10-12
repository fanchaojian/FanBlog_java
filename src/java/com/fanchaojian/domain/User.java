package com.fanchaojian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fanchaojian
 * @date 2020-9-17 - 14:43
 */
@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId ;
    private String registerMethod ; /*注册方式：localhost，qq,wechat。通过不同的登录方式获取不同的用户信息*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String name ;       /*用户名，本地注册时可用*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String email ;
    @Column(columnDefinition = "varchar(100) default ''")
    private String openId ;   /*第三方QQ登录获取到的唯一标识符*/

    @Column(columnDefinition = "varchar(100) default ''")
    private String unionId ;  /*第三方wechat登录获取到的用户唯一标识*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String icon ;        /*默认会自动分配*/
    @Column(columnDefinition = "varchar(10) default ''")
    private String gender ;
    @Column(columnDefinition = "int default 0")
    private Integer emailReply ;  /*是否进行邮件回复*/
    @Column(columnDefinition = "int default 1")
    private Integer isInuse ;     /*是否启用，1：启用。0：没有启用。当为0时可以看成是次账户已经被删除。*/

    /*mappedBy:多方的关联属性名，cascade,允许关联方关联操作此对象，fetch:立即查询此关联对象，并放入内存，保证有session进行事务的控制。*/
    @JsonIgnore
    @OneToMany(targetEntity = Comment.class,mappedBy = "user",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Comment> comments ;


    @JsonIgnore
    @OneToMany(targetEntity = Reply.class,mappedBy = "fromUser",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Reply> sourceReply = new HashSet<Reply>();

    @JsonIgnore
    @OneToMany(targetEntity = Reply.class,mappedBy = "toUser",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Reply> targetReply = new HashSet<Reply>() ;


    public User() {
    }

    public User(String registerMethod, String name, String email, String openId, String unionId, String icon, String gender, Integer emailReply, Integer isInuse, Set<Comment> comments, Set<Reply> sourceReply, Set<Reply> targetReply) {
        this.registerMethod = registerMethod;
        this.name = name;
        this.email = email;
        this.openId = openId;
        this.unionId = unionId;
        this.icon = icon;
        this.gender = gender;
        this.emailReply = emailReply;
        this.isInuse = isInuse;
        this.comments = comments;
        this.sourceReply = sourceReply;
        this.targetReply = targetReply;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRegisterMethod() {
        return registerMethod;
    }

    public void setRegisterMethod(String registerMethod) {
        this.registerMethod = registerMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getEmailReply() {
        return emailReply;
    }

    public void setEmailReply(Integer emailReply) {
        this.emailReply = emailReply;
    }

    public Integer getIsInuse() {
        return isInuse;
    }

    public void setIsInuse(Integer isInuse) {
        this.isInuse = isInuse;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Reply> getSourceReply() {
        return sourceReply;
    }

    public void setSourceReply(Set<Reply> sourceReply) {
        this.sourceReply = sourceReply;
    }

    public Set<Reply> getTargetReply() {
        return targetReply;
    }

    public void setTargetReply(Set<Reply> targetReply) {
        this.targetReply = targetReply;
    }
}
