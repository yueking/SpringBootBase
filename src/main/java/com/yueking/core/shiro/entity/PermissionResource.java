package com.yueking.core.shiro.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "y_permission_init")
public class PermissionResource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name = "url")
    private String url;

    @Basic
    @Column(name = "permission_init")
    private String permissionInit;

    @Basic
    @Column(name = "sort")
    private Integer sort;
}
