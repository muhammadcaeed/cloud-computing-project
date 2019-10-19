package com.onlinekaufen.springframework.service;

import com.onlinekaufen.springframework.dto.MessageDTO;

import java.util.List;

public interface MessengerService {

    MessageDTO createMessage(MessageDTO messsage);
    void deleteMessage(long message_id);

    void setRead(long message_id);

    List<MessageDTO> getUserMessages(long user_id);

}
