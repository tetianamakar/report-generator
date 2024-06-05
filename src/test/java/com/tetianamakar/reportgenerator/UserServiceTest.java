package com.tetianamakar.reportgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.tetianamakar.reportgenerator.entity.User;
import com.tetianamakar.reportgenerator.payload.response.UserResponse;
import com.tetianamakar.reportgenerator.repository.UserRepository;
import com.tetianamakar.reportgenerator.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("Should return users with age over given value")
    void testGetUsersByAgeOver() {
        List<User> users = generateUsers();
        when(userRepository.findAll()).thenReturn(users);
        List<UserResponse> result = userService.getUsersByAgeOver(30);
        assertEquals(2, result.size());
        assertEquals(2L, result.get(0).getId());
        assertEquals(3L, result.get(1).getId());
    }

    private List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setAge(28);
        User user2 = new User();
        user2.setId(2L);
        user2.setAge(55);
        User user3 = new User();
        user3.setId(3L);
        user3.setAge(37);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }
}
