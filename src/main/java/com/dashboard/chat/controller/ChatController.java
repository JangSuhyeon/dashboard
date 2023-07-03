package com.dashboard.chat.controller;

import com.dashboard.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("")
    public String goToChat() {

        return "page/chat/chat";
    }
}
