package com.example.ecotrack.controller;

import com.example.ecotrack.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping()
    public String chat(@RequestParam String message) {
        String response = chatService.sentToAi(message);
        return response;
    }
}