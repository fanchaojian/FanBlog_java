package com.fanchaojian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * 站点信息
 * @author fanchaojian
 * @date 2020-9-18 - 14:28
 */
@Entity
@Table(name="siteinfo")
public class SiteInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer siteId ;         /*主键 id*/
    private String siteName ;       /*博客名称*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String logoImg ;        /*博客logo*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String siteBgiUrl ;     /*背景图片*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String siteIntroUrl ;   /*站点简介*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String siteQrcode ;     /*站点二维码*/
    @Column(columnDefinition = "varchar(20) default ''")
    private String siteAge ;        /*站龄*/
    @Column(columnDefinition = "int default 0")
    private Integer visitorCount ;   /*访问总数*/
    @Column(columnDefinition = "int default 0")
    private Integer articleCount ;   /*文章总数*/

    @JsonIgnore
    @OneToOne(targetEntity = BlogAdmin.class,mappedBy = "siteInfo",fetch = FetchType.EAGER)
    private BlogAdmin blogAdmin ; /*站点信息和管理员用户是一对一关系*/

    public SiteInfo(){
    }

    public SiteInfo(String siteName, String logoImg, String siteBgiUrl, String siteIntroUrl, String siteQrcode, String siteAge, Integer visitorCount, Integer articleCount, BlogAdmin blogAdmin) {
        this.siteName = siteName;
        this.logoImg = logoImg;
        this.siteBgiUrl = siteBgiUrl;
        this.siteIntroUrl = siteIntroUrl;
        this.siteQrcode = siteQrcode;
        this.siteAge = siteAge;
        this.visitorCount = visitorCount;
        this.articleCount = articleCount;
        this.blogAdmin = blogAdmin;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getSiteBgiUrl() {
        return siteBgiUrl;
    }

    public void setSiteBgiUrl(String siteBgiUrl) {
        this.siteBgiUrl = siteBgiUrl;
    }

    public String getSiteIntroUrl() {
        return siteIntroUrl;
    }

    public void setSiteIntroUrl(String siteIntroUrl) {
        this.siteIntroUrl = siteIntroUrl;
    }

    public String getSiteQrcode() {
        return siteQrcode;
    }

    public void setSiteQrcode(String siteQrcode) {
        this.siteQrcode = siteQrcode;
    }

    public String getSiteAge() {
        return siteAge;
    }

    public void setSiteAge(String siteAge) {
        this.siteAge = siteAge;
    }

    public Integer getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public BlogAdmin getBlogAdmin() {
        return blogAdmin;
    }

    public void setBlogAdmin(BlogAdmin blogAdmin) {
        this.blogAdmin = blogAdmin;
    }
}
