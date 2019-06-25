package com.perfectstorms.dagchat.repositories;

import com.perfectstorms.dagchat.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM messages m WHERE m.chat = ?1", nativeQuery = true)
    List<Message> findByChatName(String chat);

    @Query(value = "SELECT * FROM messages m WHERE m.id = ?1", nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "SELECT * FROM messages m where m.id = :id", nativeQuery = true)
    Message getOneById(@Param("id") Long id);
}