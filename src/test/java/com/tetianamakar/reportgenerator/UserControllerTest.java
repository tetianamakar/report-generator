package com.tetianamakar.reportgenerator;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tetianamakar.reportgenerator.controller.UserController;
import com.tetianamakar.reportgenerator.payload.response.UserResponse;
import com.tetianamakar.reportgenerator.security.jwt.AuthTokenFilter;
import com.tetianamakar.reportgenerator.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = UserController.class, excludeFilters =
@ComponentScan.Filter(
    type = FilterType.ASSIGNABLE_TYPE,
    classes = AuthTokenFilter.class))
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    @DisplayName("Should return users with age over given value")
    void testGetUsersByAgeOver() throws Exception {
        List<UserResponse> userResponses = generateUserResponses();
        int age = 30;
        when(userService.getUsersByAgeOver(age)).thenReturn(userResponses);
        mockMvc.perform(get("/users/age-over/{age}", age))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[*].id").isNotEmpty())
            .andExpect(jsonPath("$[*].name").isNotEmpty())
            .andExpect(jsonPath("$[*].age").isNotEmpty());
    }

    private List<UserResponse> generateUserResponses() {
        UserResponse response1 = new UserResponse();
        response1.setId(1L);
        response1.setAge(31);
        UserResponse response2 = new UserResponse();
        response2.setId(2L);
        response2.setAge(46);
        UserResponse response3 = new UserResponse();
        response3.setId(3L);
        response3.setAge(37);
        List<UserResponse> users = new ArrayList<>();
        users.add(response1);
        users.add(response2);
        users.add(response3);
        return users;
    }

}



