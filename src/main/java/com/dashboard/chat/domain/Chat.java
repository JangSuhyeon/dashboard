package com.dashboard.chat.domain;

import javax.persistence.*;

@Entity
public class Chat {

    public enum MessageType {
        ENTER, TALK, LEAVE;
    }

    @Id
    private Long chatId;

    @Column
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column
    private String rmId;

    @Column
    private String sender;

    @Column
    private String message;

    @Column
    private String time;

}
