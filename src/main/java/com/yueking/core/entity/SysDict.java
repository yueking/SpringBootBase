package com.yueking.core.entity;

import com.yueking.core.entity.id.SysDictKey;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "sys_dict")
public class SysDict {
    @EmbeddedId
    private SysDictKey id;

    @NotNull
    @Size(max = 50)
    private String dictValue;

    @NotNull
    @Size(max = 30)
    private String parentKey;
    @NotNull
    @Size(max = 30)
    private String parentType;
    private int level;
    private boolean root;
}
