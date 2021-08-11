package com.internship.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IP_Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Role {
    @Id
    @NonNull
    @Column(name = "id")
    private short id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_disable")
    private boolean disable;
}
