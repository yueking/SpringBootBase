package com.yueking.core.entity.id;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Embeddable
public class PersonID implements Serializable {
    @NotNull
    @Size(max=10)
    private String persionId;

    @NotNull
    @Size(max = 10)
    private String groupId;



}
