package com.internship.model;

import lombok.Data;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "IP_Vote")

public class Vote {
    @Id
    private String user_ip;
    private int post_id;
    private Date voted_date;

//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;

}