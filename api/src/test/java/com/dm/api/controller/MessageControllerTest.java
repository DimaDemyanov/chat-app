package com.dm.api.controller;

import com.dm.api.service.MessageService;
import com.dm.api.service.UserService;
import com.dm.api.util.MessageTestConstants;
import com.dm.common.model.Message;
import com.dm.api.util.MessageTestConstants;
import com.dm.common.utils.ObjectMapperUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @MockBean
    private UserService userService;

    @Test
    public void testSendMessage() throws Exception {
        String messageJson = ObjectMapperUtil.getObjectMapper()
                .writeValueAsString(MessageTestConstants.SAMPLE_MESSAGE_1);

        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(messageJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReceivedMessages() throws Exception {
        Long userId = 1L;
        List<Message> messages =
                Arrays.asList(MessageTestConstants.SAMPLE_MESSAGE_1, MessageTestConstants.SAMPLE_MESSAGE_2);
        given(messageService.getReceivedMessages(userId)).willReturn(messages);

        mockMvc.perform(get("/messages/received/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetSentMessages() throws Exception {
        Long userId = 1L;
        List<Message> messages =
                Arrays.asList(MessageTestConstants.SAMPLE_MESSAGE_1, MessageTestConstants.SAMPLE_MESSAGE_2);
        given(messageService.getSentMessages(userId)).willReturn(messages);

        mockMvc.perform(get("/messages/sent/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMessagesFromUser() throws Exception {
        Long userId = 1L;
        Long senderId = 2L;
        List<Message> messages =
                Arrays.asList(MessageTestConstants.SAMPLE_MESSAGE_1, MessageTestConstants.SAMPLE_MESSAGE_2);
        given(messageService.getMessagesFromUser(userId, senderId)).willReturn(messages);

        mockMvc.perform(get("/messages/{userId}/from/{senderId}", userId, senderId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
