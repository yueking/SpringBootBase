package com.yueking.core.dao;

import com.yueking.core.entity.SysDict;
import com.yueking.core.entity.id.SysDictKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictDao extends JpaRepository<SysDict, SysDictKey> {
    List<SysDict> findAllByRoot(boolean root);
}
