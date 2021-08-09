package com.internship.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Post {
    private int id;
    private String title;
    private String content;
    private String url;
    private int totalvote;
    private Date createddate;
    private Date modifieddate;

    public Post() {
    }

    public Post(String title, String content, String originalFilename) {
        this.title = title;
        this.content = content;
        this.url = originalFilename;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotalvote() {
        return totalvote;
    }

    public void setTotalvote(int totalVote) {
        this.totalvote = totalVote;
    }


    public Date getCreateddate() {
        return new Date(System.currentTimeMillis());
    }

    public void setCreateddate(Date createdDate) {
        this.createddate = createdDate;
    }

    public Date getModifieddate() {

        return new Date(System.currentTimeMillis());
    }

    public void setModifieddate(Date modifiedDate) {
        this.modifieddate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
