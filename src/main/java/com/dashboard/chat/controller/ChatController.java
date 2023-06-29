package com.dashboard.chat.controller;

import com.dashboard.chat.domain.dto.ChatRoomResponseDTO;
import com.dashboard.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("")
    public String goToChat() {

        // 채팅방 목록 조회
        List<ChatRoomResponseDTO> chatRmResList = chatService.findAll();

        return "page/chat/chat";
    }
}
