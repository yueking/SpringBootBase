package com.yueking.core.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Entity
@Table(name = "y_roles",
        indexes = {
                @Index(name = "idx_sys_roles_role", columnList = "role",unique = true)
        }
)
public class SysRolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 100)
    private String role;
    @Size(max = 100)
    private String description;
    @Basic
    private boolean available;
    @Basic
    private String name;


}
