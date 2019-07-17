package com.yueking.core.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "y_users_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<SysRolesEntity> roles;



}
