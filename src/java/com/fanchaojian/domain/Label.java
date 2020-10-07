package com.fanchaojian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 标签表，与文章表是一对多关系  这是1的一方
 * @author fanchaojian
 * @date 2020-9-18 - 14:48
 */
@Entity
@Table(name="label")
public class Label implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer labelId ;
    @Column(columnDefinition = "varchar(20) default ' ' ")
    private String labelName ;      /*标签名称*/
    @Column(columnDefinition = "int default 0")
    private Integer articleCount ;   /*文章总数*/
    private Date createDate ;     /*创建时间*/
    @Column(columnDefinition = "varchar(300) default ' ' ")
    private String remark ;         /*备注*/

    /*mappedBy=“多端的关联属性名”
    * cascade：级联操纵，表示当主控方执行操作时，关联对象（被动方）是否同步执行同一操作*/
    @JsonIgnore
    @OneToMany(targetEntity = Article.class,mappedBy = "label",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Article> articles = new HashSet<Article>();

    public Label() {
    }

    public Label(Integer labelId, String labelName, Integer articleCount, Date createDate, String remark, Set<Article> articles) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.articleCount = articleCount;
        this.createDate = createDate;
        this.remark = remark;
        this.articles = articles;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }


}
