package com.internship.model;

import lombok.Data;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "IP_Vote", uniqueConstraints={
        @UniqueConstraint(columnNames = {"post_id", "user_ip"})
})

public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String post_id;
    private String user_ip;
    private Date voted_date;

//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;

}