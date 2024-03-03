package com.dm.integration;

import com.dm.api.ApiApplication;
import com.dm.common.model.Message;
import com.dm.common.repository.MessageRepository;
import com.dm.consumer.ConsumerApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {ApiApplication.class, ConsumerApplication.class})
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, topics = {"messages"}, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @AfterEach
    void tearDown() {
        // Clear the database after each test
        messageRepository.deleteAll();
    }

    @Test
    public void testSendMessageAndStoreInDb() throws Exception {
        String messageJson = "{\"senderId\":1,\"receiverId\":2,\"content\":\"Hello from John to Jane\"}";

        // Sending message via REST controller
        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(messageJson))
                .andExpect(status().isOk());

        // Add a small sleep to allow the message to be processed
        Thread.sleep(3000); // Consider using Awaitility or another mechanism to wait for the result

        // Verifying the message is stored in the database
        Message message = messageRepository.findBySenderId(Long.valueOf(1)).get(0);
        assertEquals("Hello from John to Jane", message.getContent());
        assertEquals(Long.valueOf(1), message.getSenderId());
        assertEquals(Long.valueOf(2), message.getReceiverId());
    }

    @Test
    public void testRetrieveSentMessages() throws Exception {
        // Pre-populate the database with a message (this could be part of a setup method)
        Message message = new Message();
        message.setSenderId(1L);
        message.setReceiverId(2L);
        message.setContent("Test message");
        messageRepository.save(message);

        // Retrieve sent messages for user 1
        mockMvc.perform(get("/messages/sent/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Test message"))
                .andExpect(jsonPath("$[0].senderId").value(1))
                .andExpect(jsonPath("$[0].receiverId").value(2));
    }

    @Test
    public void testRetrieveReceivedMessages() throws Exception {
        // Pre-populate the database with a message
        Message message = new Message();
        message.setSenderId(1L);
        message.setReceiverId(2L);
        message.setContent("Another test message");
        messageRepository.save(message);

        // Retrieve received messages for user 2
        mockMvc.perform(get("/messages/received/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Another test message"))
                .andExpect(jsonPath("$[0].senderId").value(1))
                .andExpect(jsonPath("$[0].receiverId").value(2));
    }
}
