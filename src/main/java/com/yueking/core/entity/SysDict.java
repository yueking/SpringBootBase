package com.yueking.core.entity;

import com.yueking.core.entity.id.SysDictKey;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "sys_dict")
public class SysDict implements Serializable {
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

    public SysDict(){
        subDictList = new HashSet<>();
    }

    /**
     * root dict
     * @param dictType
     * @param dictValue
     */
    public SysDict(String dictType, String dictValue){
        SysDictKey  key = new SysDictKey(dictType,dictType);
        this.setId(key);
        this.setDictValue(dictValue);
        this.setRoot(true);
        this.setLevel(0);
        this.subDictList = new HashSet<>();
    }

    /**
     * sub dict
     * @param dictKey
     * @param dictValue
     * @param parentDict
     */
    public SysDict(String dictKey, String dictValue, SysDict parentDict){
        SysDictKey  key = new SysDictKey(dictKey,parentDict.getId().getDictType());
        this.setId(key);
        this.setDictValue(dictValue);
        this.setLevel(parentDict.getLevel()+1);

        this.setParentKey(parentDict.getId().getDictKey());
        this.setParentType(parentDict.getId().getDictType());
        this.subDictList = new HashSet<>();
        parentDict.getSubDictList().add(this);
    }

}
