package com.yueking.core.entity;

import com.yueking.core.entity.id.PersonID;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 测试配置 双键
 */
@Data
@Entity
@Table(name="person")
public class Person {
    @EmbeddedId
    private PersonID id;

    @NotNull
    @Size(max = 30)
    private String name;



}
