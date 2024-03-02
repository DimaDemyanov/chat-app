package com.dm.api.service;

import com.dm.api.util.MessageTestConstants;
import com.dm.common.repository.MessageRepository;
import com.dm.common.model.Message;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private KafkaTemplate<String, Message> kafkaTemplate;

    @InjectMocks
    private MessageService messageService;

    @Test
    public void testSendMessage() {
        Message message = MessageTestConstants.SAMPLE_MESSAGE_1;
        messageService.sendMessage(message);

        // Verify that the Kafka template's send method was called
        verify(kafkaTemplate, times(1)).send(anyString(), eq(message));
    }

    @Test
    public void testGetReceivedMessages() {
        Long userId = 1L;
        List<Message> messages =
                Arrays.asList(MessageTestConstants.SAMPLE_MESSAGE_1, MessageTestConstants.SAMPLE_MESSAGE_2);

        when(messageRepository.findByReceiverId(userId)).thenReturn(messages);

        List<Message> result = messageService.getReceivedMessages(userId);

        assertThat(result).hasSize(2);
        verify(messageRepository, times(1)).findByReceiverId(userId);
    }

    @Test
    public void testGetSentMessages() {
        Long userId = 1L;
        List<Message> messages =
                Arrays.asList(MessageTestConstants.SAMPLE_MESSAGE_1, MessageTestConstants.SAMPLE_MESSAGE_2);

        when(messageRepository.findBySenderId(userId)).thenReturn(messages);

        List<Message> result = messageService.getSentMessages(userId);

        assertThat(result).hasSize(2);
        verify(messageRepository, times(1)).findBySenderId(userId);
    }

    @Test
    public void testGetMessagesFromUser() {
        Long userId = 1L;
        Long senderId = 2L;
        List<Message> messages =
                Arrays.asList(MessageTestConstants.SAMPLE_MESSAGE_1, MessageTestConstants.SAMPLE_MESSAGE_2);

        when(messageRepository.findBySenderIdAndReceiverId(senderId, userId)).thenReturn(messages);

        List<Message> result = messageService.getMessagesFromUser(userId, senderId);

        assertThat(result).hasSize(2);
        verify(messageRepository, times(1)).findBySenderIdAndReceiverId(senderId, userId);
    }
}
