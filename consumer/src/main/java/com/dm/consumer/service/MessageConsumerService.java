package com.dm.consumer.service;

import com.dm.common.model.Message;
import com.dm.common.repository.MessageRepository;
import com.dm.common.utils.LoggerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumerService.class);

    @Autowired
    private MessageRepository messageRepository;

    @KafkaListener(topics = "messages", groupId = "message-consumer-group")
    public void listen(Message message) {
        logger.info("Received message: {}", LoggerHelper.toJson(message));
        messageRepository.save(message);
        logger.info("Message saved with ID: {}", message.getId());
    }
}
