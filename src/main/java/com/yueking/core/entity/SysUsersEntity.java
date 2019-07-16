package com.yueking.core.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "y_users",
        indexes = {
                @Index(name = "idx_sys_users_username", columnList = "username",unique = true)
        }
)
public class SysUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 3, max = 100)
    private String username;
    @Size(min = 3, max = 100)
    private String password;
    @Size(max = 100)
    private String salt;
    @Basic
    private boolean locked;



}
