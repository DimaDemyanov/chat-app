package com.dm.consumer.service;

import com.dm.common.model.Message;
import com.dm.common.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageConsumerServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageConsumerService messageConsumerService;

    @Test
    public void testListen() {
        // Create a test message
        Message testMessage = new Message(1L, 2L, "Sample message from user 1 to user 2");

        // Directly call the listen method to simulate a message reception
        messageConsumerService.listen(testMessage);

        // Verify that the message was saved
        verify(messageRepository, times(1)).save(testMessage);
    }
}
