package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true") // Allow frontend access
public class HelloController {

    private final MessageRepository messageRepository;

    public HelloController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Save message (POST)
    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody Message message) {
        if (message.getContent() == null || message.getContent().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Content cannot be empty\"}");
        }
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    // Get all messages (GET)
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageRepository.findAll());
    }
}

