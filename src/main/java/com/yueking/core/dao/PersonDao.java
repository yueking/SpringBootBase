package com.yueking.core.dao;

import com.yueking.core.entity.Person;
import com.yueking.core.entity.id.PersonID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, PersonID> {
}
