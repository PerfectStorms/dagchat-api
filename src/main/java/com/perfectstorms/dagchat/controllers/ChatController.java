package com.perfectstorms.dagchat.controllers;

import com.perfectstorms.dagchat.entities.Chat;
import com.perfectstorms.dagchat.entities.Message;
import com.perfectstorms.dagchat.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    private ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping(value = "/chats")
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public ResponseEntity addMessage(@RequestBody Chat chat) {
        chatRepository.save(new Chat(
                chat.getId(),
                chat.getName(),
                chat.getIsPrivate()
        ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/chats", method = RequestMethod.DELETE)
    public ResponseEntity deleteMessage(@RequestBody Long id) {
        chatRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
