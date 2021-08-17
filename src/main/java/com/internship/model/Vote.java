package com.internship.model;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "IP_Vote")

public class Vote implements Serializable {
    @Id
    @Column(name = "user_ip")
    private String userIP;

    @Column(name = "post_id")
    private int postID;

    @Column(name = "voted_date")
    private Date votedDate;

//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;

}