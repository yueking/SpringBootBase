package com.yueking.core.entity;

import com.yueking.core.entity.id.SysDictKey;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "sys_dict")
public class SysDict {
    @EmbeddedId
    private SysDictKey id;

    @NotNull
    @Size(max = 50)
    private String dictValue;

    @Size(max = 30)
    private String parentKey;
    @Size(max = 30)
    private String parentType;
    private int level;
    private boolean root;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sys_dict_join")
    private Set<SysDict> subDictList;
}
