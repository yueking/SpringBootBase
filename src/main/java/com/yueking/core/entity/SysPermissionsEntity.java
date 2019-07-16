package com.yueking.core.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "y_permissions",
        indexes = {
                @Index(name = "idx_sys_permissions_permission", columnList = "permission", unique = true)
        }
)
public class SysPermissionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 100)
    private String permission;
    @Size(max = 100)
    private String description;
    @Basic
    private boolean available;




}
