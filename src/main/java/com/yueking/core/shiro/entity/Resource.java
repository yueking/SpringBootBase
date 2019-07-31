package com.yueking.core.shiro.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "y_resource")
public class Resource implements Serializable {
    @Size(max = 33)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;


    @Size(min=2,max=100)
    private String name;

    @Size(max=20)
    private String type;
}
