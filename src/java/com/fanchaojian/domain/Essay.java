package com.fanchaojian.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 心情随笔
 * @author fanchaojian
 * @date 2020-9-18 - 14:46
 */
@Entity
@Table(name="essay")
public class Essay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer essayId ;
    private Date createDate ;
    @Column(columnDefinition = "varchar(200) default '' ")
    private String content ;

    public Essay() {
    }

    public Essay(Date createDate, String content) {
        this.createDate = createDate;
        this.content = content;
    }

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Essay{" +
                "essayId=" + essayId +
                ", createDate=" + createDate +
                ", content='" + content + '\'' +
                '}';
    }
}
