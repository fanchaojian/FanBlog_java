package com.fanchaojian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 站点管理员用户
 * @author fanchaojian
 * @date 2020-9-18 - 11:29
 */
@Entity
@Table(name = "blogadmin")
public class BlogAdmin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;         /*主键 id*/
    @JsonIgnore
    private String username ;       /*用户名*/
    @JsonIgnore
    private String password ;       /*密码*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String name ;           /*昵称*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String job ;            /*职业*/
    @Column(columnDefinition = "varchar(50) default ''")
    private String email ;          /*邮箱*/
    @Column(columnDefinition = "varchar(50) default ''")
    private String place  ;          /*籍贯*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String hobby ;          /*爱好*/
    @Column(columnDefinition = "varchar(300) default ''")
    private String motto ;          /*格言*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String introUrl ;       /*简介地址*/
    @Column(columnDefinition = "varchar(10) default ''")
    private String gender ;         /*性别*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String iconUrl ;        /*头像地址*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String qqQrcode ;       /*QQ二维码*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String wechatQrcode ;   /*微信二维码*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String weboQrcode ;     /*微博二维码*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String gghQrcode ;      /*公众号二维码*/
    @Column(columnDefinition = "varchar(100) default ''")
    private String githubUrl ;      /*github地址*/
    @JsonIgnore
    @Column(columnDefinition = "int default 0")
    private Integer preOrder ; /*用于排序，用于随意切换网站所有*/

    @OneToOne(targetEntity = SiteInfo.class)
    @JoinColumn(name="siteId")
    private SiteInfo siteInfo;  /*管理员用户与站点信息是一对一的关系*/

    public BlogAdmin() {
    }

    public BlogAdmin(String username, String password, String name, String job, String email, String place, String hobby, String motto, String introUrl, String gender, String iconUrl, String qqQrcode, String wechatQrcode, String weboQrcode, String gghQrcode, String githubUrl, Integer preOrder, SiteInfo siteInfo) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.job = job;
        this.email = email;
        this.place = place;
        this.hobby = hobby;
        this.motto = motto;
        this.introUrl = introUrl;
        this.gender = gender;
        this.iconUrl = iconUrl;
        this.qqQrcode = qqQrcode;
        this.wechatQrcode = wechatQrcode;
        this.weboQrcode = weboQrcode;
        this.gghQrcode = gghQrcode;
        this.githubUrl = githubUrl;
        this.preOrder = preOrder;
        this.siteInfo = siteInfo;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getIntroUrl() {
        return introUrl;
    }

    public void setIntroUrl(String introUrl) {
        this.introUrl = introUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getQqQrcode() {
        return qqQrcode;
    }

    public void setQqQrcode(String qqQrcode) {
        this.qqQrcode = qqQrcode;
    }

    public String getWechatQrcode() {
        return wechatQrcode;
    }

    public void setWechatQrcode(String wechatQrcode) {
        this.wechatQrcode = wechatQrcode;
    }

    public String getWeboQrcode() {
        return weboQrcode;
    }

    public void setWeboQrcode(String weboQrcode) {
        this.weboQrcode = weboQrcode;
    }

    public String getGghQrcode() {
        return gghQrcode;
    }

    public void setGghQrcode(String gghQrcode) {
        this.gghQrcode = gghQrcode;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public Integer getPreOrder() {
        return preOrder;
    }

    public void setPreOrder(Integer preOrder) {
        this.preOrder = preOrder;
    }

    public SiteInfo getSiteInfo() {
        return siteInfo;
    }

    public void setSiteInfo(SiteInfo siteInfo) {
        this.siteInfo = siteInfo;
    }
}
