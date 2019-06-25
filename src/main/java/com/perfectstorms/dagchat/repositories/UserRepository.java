package com.perfectstorms.dagchat.repositories;

import com.perfectstorms.dagchat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users u where u.id = :id", nativeQuery = true)
    User getOneById(@Param("id") Long id);

    @Query(value = "SELECT * FROM users u where u.username = :name", nativeQuery = true)
    User getOneByName(@Param("name") String name);
}