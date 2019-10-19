package com.onlinekaufen.springframework.service.mySql;

import com.onlinekaufen.springframework.dao.MessengerDAO;
import com.onlinekaufen.springframework.dto.MessageDTO;
import com.onlinekaufen.springframework.service.MessengerService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessengerServiceImpl implements MessengerService {

    private final
    DbService dbService;

    public MessengerServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }


    @Override
    public MessageDTO createMessage(MessageDTO messsage) {
        return null;
    }

    @Override
    public void deleteMessage(long message_id) {

    }

    @Override
    public void setRead(long message_id) {

    }

    @Override
    public List<MessageDTO> getUserMessages(long user_id) {
        List<MessageDTO> messages;
        MessengerDAO messageDAO = dbService.getDao(MessengerDAO.class);
        messages = messageDAO.getMessageByReceiver(user_id);
        return messages;
    }
}
