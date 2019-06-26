package com.perfectstorms.dagchat.controllers;

import com.perfectstorms.dagchat.entities.Message;
import com.perfectstorms.dagchat.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Message Controller.
 *
 * @author mamadaliev
 *
 */
@RestController
public class MessageController {

    private MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Get all messages.
     *
     * @return List of Message entity class.
     */
    @GetMapping(value = "/messages")
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    /**
     * Get list of the Message entity class by chat name.
     *
     * @param name A chat name.
     * @return List of Message entity class.
     */
    @GetMapping(value = "/messages/{name}")
    public List<Message> getAllByChat(@PathVariable String name) {
        return messageRepository.findByChatName(name);
    }

    @GetMapping(value = "/message/{id}")
    public Message getOneById(@PathVariable Long id) {
        return messageRepository.getOneById(id);
    }

    /**
     * Add message.
     *
     * @param message Message entity class.
     * @return ResponseEntity class.
     */
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity addMessage(@RequestBody Message message) {
        messageRepository.save(new Message(
                message.getId(),
                message.getUsername(),
                message.getMessage(),
                message.getChat(),
                message.getTimes()
        ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Delete message.
     *
     * @param id Identification number of the message.
     * @return ResponseEntity class.
     */
    @RequestMapping(value = "/messages", method = RequestMethod.DELETE)
    public ResponseEntity deleteMessage(@RequestBody Long id) {
        messageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}