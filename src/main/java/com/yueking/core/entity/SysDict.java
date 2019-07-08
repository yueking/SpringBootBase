package com.yueking.core.entity;

import com.yueking.core.entity.id.SysDictKey;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)

    @JoinColumns({
            @JoinColumn(name = "parentKey", referencedColumnName = "dictKey", insertable = false, updatable = false),
            @JoinColumn(name = "parentType", referencedColumnName = "dictType", insertable = false, updatable = false)
    })

    private Set<SysDict> subDictList;
//todo dict method
    public SysDict(){
        subDictList = new HashSet<>();
    }
    public SysDict(String dictKey, String dictValue){
        super();
        SysDictKey  key = new SysDictKey(dictKey,dictKey);
        this.setId(key);
        this.setDictValue(dictValue);
        this.subDictList = new HashSet<>();
    }
    public SysDict(String dictKey, String dictValue, SysDict parentDict){
        SysDictKey  key = new SysDictKey(dictKey,parentDict.getId().getDictType());
        this.setId(key);
        this.setDictValue(dictValue);

        this.setParentKey(parentDict.getId().getDictKey());
        this.setParentType(parentDict.getId().getDictType());
        this.subDictList = new HashSet<>();
    }

    public SysDict(String dictKey, String dictValue, String dictType){
        SysDictKey  key = new SysDictKey(dictKey,dictType);
        this.setId(key);
        this.setDictValue(dictValue);
        this.subDictList = new HashSet<>();
    }
}
