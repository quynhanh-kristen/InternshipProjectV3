package com.internship.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "IP_Account", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {
    @Id
    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "pwd")
    private String password;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "is_banned")
    private boolean banned;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
}
