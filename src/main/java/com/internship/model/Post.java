package com.internship.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "IP_Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String fileID;
    private int totalVote;
    private Date createdDate;
    private Date modifiedDate;
    private String createdUser;
    private String fileType;

    public Post(){

    }

    public Post(String title, String content, String originalFilename) {
        this.title = title;
        this.content = content;
        this.fileID = originalFilename;
    }

}
