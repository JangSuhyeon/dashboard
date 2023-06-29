package com.dashboard.chat.repository;

import com.dashboard.chat.domain.ChatRoom;

import java.util.List;

public interface ChatRepository {
    List<ChatRoom> findAll();
}
