package com.dashboard.chat.service;

import com.dashboard.chat.domain.ChatRoom;
import com.dashboard.chat.domain.dto.ChatRoomResponseDTO;
import com.dashboard.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public List<ChatRoomResponseDTO> findAll() {
        List<ChatRoomResponseDTO> chatRmResDtoList = new ArrayList<>();

        List<ChatRoom> chatRmList = chatRepository.findAll();
        for (ChatRoom chatRoom : chatRmList) {
            ChatRoomResponseDTO chatRmResDto = chatRoom.toDto();
            chatRmResDtoList.add(chatRmResDto);
        }

        return chatRmResDtoList;
    }
}
