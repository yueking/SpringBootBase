package com.yueking.core.entity.id;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 联合主键
 */
@Data
@Embeddable
public class SysDictKey implements Serializable {
    @NotNull
    @Size(max = 30)
    @Column(name = "dict_key")
    private String dictKey;

    @NotNull
    @Size(max = 30)
    @Column(name = "dict_type")
    private String dictType;

    public SysDictKey(){}

    public SysDictKey(String dictKey, String dictType) {
        this.dictKey = dictKey;
        this.dictType = dictType;
    }
}
