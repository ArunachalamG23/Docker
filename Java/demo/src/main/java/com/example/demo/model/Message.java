package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Prevents null content
    private String content;

    // Constructors
    public Message() {}

    public Message(String content) {
        this.content = content;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}

