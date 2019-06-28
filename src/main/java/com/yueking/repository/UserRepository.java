package com.yueking.repository;

import com.yueking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    User findByUsername(String username);

    Boolean existsByUsername(String username);


//    @Query("select u from User u where u.id =:id and u.name = :name")
//    User queryUsersByYW(@Param("id") int id, @Param("name") String name);
//    User queryUsersByYW(int id, String name);

    @Query("select u from User u where u.id =?1 and u.name = ?2")
    User queryUsersByYW(int id, String name);


}
