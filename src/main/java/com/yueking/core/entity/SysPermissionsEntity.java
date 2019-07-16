package com.yueking.core.entity;

import javax.persistence.*;
import java.util.Objects;

//@Entity
@Table(name = "sys_permissions", schema = "db", catalog = "")
public class SysPermissionsEntity {
    private long id;
    private String permission;
    private String description;
    private Byte available;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "permission")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "available")
    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPermissionsEntity that = (SysPermissionsEntity) o;
        return id == that.id &&
                Objects.equals(permission, that.permission) &&
                Objects.equals(description, that.description) &&
                Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, permission, description, available);
    }
}
