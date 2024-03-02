package com.dm.api.service;

import com.dm.common.model.User;
import com.dm.common.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static final User TEST_USER = createTestUser();

    private static User createTestUser() {
        User user = new User();
        user.setId(1L);
        user.setNickname("john_doe");
        return user;
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(TEST_USER);

        User createdUser = userService.createUser(TEST_USER.getNickname());

        assertThat(createdUser.getNickname()).isEqualTo(TEST_USER.getNickname());
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(TEST_USER.getId())).thenReturn(Optional.of(TEST_USER));

        User foundUser = userService.getUserById(TEST_USER.getId());

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(TEST_USER.getId());
    }

    @Test
    public void testGetUserByNickname() {
        when(userRepository.findByNickname(TEST_USER.getNickname())).thenReturn(TEST_USER);

        User foundUser = userService.getUserByNickname(TEST_USER.getNickname());

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getNickname()).isEqualTo(TEST_USER.getNickname());
    }
}
