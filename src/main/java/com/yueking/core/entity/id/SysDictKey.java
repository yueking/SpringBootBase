package com.yueking.core.entity.id;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 联合主键
 */
@Data
@Embeddable
public class SysDictKey {
    @NotNull
    @Size(max = 30)
    private String dictKey;

    @NotNull
    @Size(max = 30)
    private String dictType;
}
