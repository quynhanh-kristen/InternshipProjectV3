package com.internship.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "IP_Post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name ="content")
    private String content;
    @Column(name="file_id")
    private String fileID;
    @Column(name="total_vote")
    private int totalVote;

    @Column(name="created_date")
    private Date createdDate;
    @Column(name="modified_date")
    private Date modifiedDate;
    @Column(name = "created_user")
    private String createdUser;
    @Column(name = "file_type")
    private String fileType;

    public Post(){

    }

    public Post(int id, String title, String content, String fileID, int totalVote, Date createdDate, Date modifiedDate, String createdUser, String fileType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.fileID = fileID;
        this.totalVote = totalVote;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdUser = createdUser;
        this.fileType = fileType;
    }

    public Post(String title, String content, String fileType) {
        this.content = content;
        this.title = title;
        this.fileType = fileType;
    }

//    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
//    private List<Vote> voteList;
}
