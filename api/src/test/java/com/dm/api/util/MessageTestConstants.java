package com.dm.api.util;

import com.dm.common.model.Message;
import java.time.LocalDateTime;

public class MessageTestConstants {

    public static final Message SAMPLE_MESSAGE_1 = createSampleMessage(1L, 1L, 2L, "Sample message from user 1 to user 2");
    public static final Message SAMPLE_MESSAGE_2 = createSampleMessage(2L, 2L, 1L, "Sample message from user 2 to user 1");

    private static Message createSampleMessage(Long id, Long senderId, Long receiverId, String content) {
        Message message = new Message();
        message.setId(id);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return message;
    }
}