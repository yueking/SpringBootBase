package com.yueking.core.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "y_roles_permissions",joinColumns = @JoinColumn(name="role_id"),inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<SysPermissionsEntity> permissions;


}
