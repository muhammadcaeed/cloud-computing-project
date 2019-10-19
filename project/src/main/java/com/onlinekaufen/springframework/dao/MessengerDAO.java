package com.onlinekaufen.springframework.dao;

import com.onlinekaufen.springframework.dto.MessageDTO;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface MessengerDAO {

    @SqlQuery("SELECT users.firstName, users.lastName,users.emailID,chat_messages.id," +
            "CAST(chat_messages.sent_date AS CHAR CHARACTER SET utf8) AS sent_date," +
            "chat_messages.message,chat_messages.author_id,chat_messages.read FROM chat_messages " +
            "INNER JOIN users ON chat_messages.author_id=users.id WHERE chat_messages.receiver_id=:receiverId")
    List<MessageDTO> getMessageByReceiver(@Bind("receiverId") long receiverId);
}
