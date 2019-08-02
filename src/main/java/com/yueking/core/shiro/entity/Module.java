package com.yueking.core.shiro.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "y_module")
public class Module implements Serializable {
    @Size(max = 33)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Size(min=2,max=100)
    private String name;

    @Size(max=20)
    private String type;

    private int level;

    private boolean root;

    private int sort;

    @Size(max = 33)
    private String parentId;

//    @Size(max = 33)
//    private String resourceId;

//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name="resourceId",referencedColumnName="id",insertable = true)
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="resource_id")//关联的表为address表，其主键是id
    private Resource resource;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId", referencedColumnName = "id", insertable = true, updatable = false)
    private Set<Module> subModules;

    public Module (){
        this.subModules = new HashSet<Module>();
    }

    public Module(String name){
        this.setName(name);
        this.setRoot(true);
        this.setLevel(0);
        this.subModules = new HashSet<>();
    }


    public Module(String name, Module parentDict){
        this.setName(name);
        this.setLevel(parentDict.getLevel()+1);
        this.setParentId(parentDict.getId());
        this.subModules = new HashSet<>();
        parentDict.getSubModules().add(this);
    }

}
