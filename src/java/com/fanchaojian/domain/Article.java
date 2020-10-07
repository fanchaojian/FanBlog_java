package com.fanchaojian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * 博客文章
 * @author fanchaojian
 * @date 2020-9-18 - 14:35
 */
@Entity
@Table
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId ;                                  /*主键 id*/
    @Column(columnDefinition = "varchar(100) default '' ")
    private String title ;                                        /*文章标题*/
    @Column(columnDefinition = "varchar(100) default '' ")
    private String author ;                                       /*文章作者*/
    @Column(columnDefinition = "varchar(100) default '' ")
    private String iconUrl ;                                    /*简介图片*/
    @Column(columnDefinition = "varchar(300) default '' ")
    private String intro ;                                      /*文章简介*/
    @Column(columnDefinition = "varchar(8000) default '' ")
    private String content ;                                    /*文章正文*/
    private Date createDate;                      /*创建时间*/
    @Column(columnDefinition = "int default 0")
    private Integer commentCount ;                              /*留言数量*/
    @Column(columnDefinition = "int default 0")
    private Integer readCount;                                  /*阅读数量*/
    @Column(columnDefinition = "int default 0")
    private Integer likeCount;                             /*获赞数*/
    @Column(columnDefinition = "varchar(100) default '' ")
    private String reprintSite ;                           /*转载地址*/

    /*所属标签*/
    @ManyToOne(targetEntity = Label.class)
    @JoinColumn(name = "labelid")
    private Label label = new Label();

    public Article() {
    }

    public Article(String title, String author, String iconUrl, String intro, String content, Date createDate, String reprintSite,Integer commentCount,Integer readCount,Integer likeCount) {
        this.title = title;
        this.author = author;
        this.iconUrl = iconUrl;
        this.intro = intro;
        this.content = content;
        this.createDate = createDate;
        this.reprintSite = reprintSite;
        this.commentCount = commentCount ;
        this.readCount = commentCount ;
        this.likeCount = likeCount ;
    }

    public Article(String title, String author, String iconUrl, String intro, String content, Date createDate, Integer commentCount, Integer readCount, Integer likeCount, String reprintSite, Label label) {
        this.title = title;
        this.author = author;
        this.iconUrl = iconUrl;
        this.intro = intro;
        this.content = content;
        this.createDate = createDate;
        this.commentCount = commentCount;
        this.readCount = readCount;
        this.likeCount = likeCount;
        this.reprintSite = reprintSite;
        this.label = label;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getReprintSite() {
        return reprintSite;
    }

    public void setReprintSite(String reprintSite) {
        this.reprintSite = reprintSite;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", intro='" + intro + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", commentCount=" + commentCount +
                ", readCount=" + readCount +
                ", likeCount=" + likeCount +
                ", reprintSite='" + reprintSite + '\'' +
                ", label=" + label +
                '}';
    }
}