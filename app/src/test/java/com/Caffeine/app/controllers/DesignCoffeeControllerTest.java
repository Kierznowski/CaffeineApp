package com.Caffeine.app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DesignCoffeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void notUserShouldReturnLoginPage() throws Exception {
        this.mockMvc.perform(get("/design"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

    }

    @Test
    @WithMockUser(username="test", roles={"USER"})
    void loggedUserShouldReturnDesignPage() throws Exception {

        this.mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"));
    }


}
