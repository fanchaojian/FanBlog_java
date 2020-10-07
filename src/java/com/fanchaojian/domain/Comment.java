package com.fanchaojian.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**评论表
 * @author fanchaojian
 * @date 2020-9-24 - 14:03
 */
@Entity
@Table(name="comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String type ;           /*评论类型，用于区分是留言还是评论。comment,guestbook*/
    @Column(columnDefinition = "int default 0")
    private Integer articleId ;     /*文章id：为了与博客文章减少关联，这儿就不用外键了，手动添加。当评论为留言的时候，此项为默认值0*/
    @Column(columnDefinition = "varchar(500) default '' ")
    private String content ;        /*评论内容*/
    private Date createDate ;       /*创建时间，记录生成时生成*/

    /*评论用户，评论与用户是多对一的关系，外键：userId*/
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user ;  /*评论用户*/

    /*评论下的回复，一对多关系。允许级联删除回复*/
    @OneToMany(targetEntity = Reply.class,mappedBy = "comment",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Reply> replys = new HashSet<Reply>();

    public Comment() {
    }

    public Comment(String type, Integer articleId, String content, Date createDate, User user, Set<Reply> replys) {
        this.type = type;
        this.articleId = articleId;
        this.content = content;
        this.createDate = createDate;
        this.user = user;
        this.replys = replys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Reply> getReplys() {
        return replys;
    }

    public void setReplys(Set<Reply> replys) {
        this.replys = replys;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
