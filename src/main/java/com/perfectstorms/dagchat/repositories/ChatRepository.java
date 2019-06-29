package com.perfectstorms.dagchat.repositories;

import com.perfectstorms.dagchat.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "SELECT * FROM chats c ORDER BY id DESC ", nativeQuery = true)
    List<Chat> getAll();

    @Query(value = "SELECT * FROM chats c where c.name = :name", nativeQuery = true)
    Chat hasChatByName(@Param("name") String name);
}
