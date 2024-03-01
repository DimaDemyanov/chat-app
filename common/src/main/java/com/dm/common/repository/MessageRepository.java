package com.dm.common.repository;

import com.dm.common.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverId(Long receiverId);
    List<Message> findBySenderId(Long senderId);
    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}