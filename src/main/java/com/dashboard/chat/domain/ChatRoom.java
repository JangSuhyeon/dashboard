package com.dashboard.chat.domain;

import com.dashboard.chat.domain.dto.ChatRoomResponseDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ChatRoom {

        @Id
        private Long rmId;

        @Column
        private String rmNm;

        @Column
        private int userCnt;

        public ChatRoomResponseDTO toDto() {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(this, ChatRoomResponseDTO.class);
        }

}
