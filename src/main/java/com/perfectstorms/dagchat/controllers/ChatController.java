package com.perfectstorms.dagchat.controllers;

import com.perfectstorms.dagchat.entities.Chat;
import com.perfectstorms.dagchat.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
public class ChatController {

    private ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping(value = "/chats")
    public List<Chat> getAll() {
        return chatRepository.getAll();
    }

    @GetMapping(value = "/chats/{name}")
    public Chat getOneByName(@PathVariable String name) {
        return chatRepository.hasChatByName(name);
    }

    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public ResponseEntity addMessage(@RequestBody Chat chat) {
        chatRepository.save(chat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/chats", method = RequestMethod.DELETE)
    public ResponseEntity deleteMessage(@RequestBody Long id) {
        chatRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
