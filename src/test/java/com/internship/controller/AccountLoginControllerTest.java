package com.internship.controller;

import com.internship.utils.AuthenticationCheckingUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith({MockitoExtension.class})
class AccountLoginControllerTest {
    private final String LOGIN_PAGE = "loginPage";

    private MockMvc mockMvc;

    @InjectMocks
    private AccountLoginController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void givenUnauthenticatedUser_whenAccessLoginPageByGet_thenReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name(LOGIN_PAGE));
    }

    @Test
    void givenAuthenticatedUser_whenAccessLoginPageByGet_thenReturnIndex() throws Exception {
        MockedStatic<AuthenticationCheckingUtils> utils = Mockito.mockStatic(AuthenticationCheckingUtils.class);

        utils.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(true);

        mockMvc.perform(get("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}