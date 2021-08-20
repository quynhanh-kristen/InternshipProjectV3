package com.internship.controller;

import com.internship.dto.AccountRegistrationDTO;
import com.internship.service.IAccountService;
import com.internship.utils.AuthenticationCheckingUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({MockitoExtension.class})
class AccountRegisterControllerTest {
    private final String REGISTRATION_PAGE = "registrationPage";
    private final String LOGIN_PAGE = "loginPage";

    @Mock
    private IAccountService service;

    @InjectMocks
    private AccountRegisterController controller;

    private MockMvc mockMvc;
    private MockedStatic<AuthenticationCheckingUtils> utilsMock;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        utilsMock = Mockito.mockStatic(AuthenticationCheckingUtils.class);
    }

    @AfterEach
    void tearDown() {
        utilsMock.close();
    }

    @Test
    void givenUnauthenticatedUser_whenAccessRegistrationPageByGet_thenReturnRegistrationPage() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(false);
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTRATION_PAGE));
    }

    @Test
    void givenAuthenticatedUser_whenAccessRegistrationPageByGet_thenReturnIndex() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(true);

        mockMvc.perform(get("/registration"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void givenAllFieldsIsValid_whenAccessRegistrationPageByPost_thenReturnLoginPage() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(false);
        String username = "valid";
        String password = "12345678";
        String fullName = "Valid User";
        String email = "validUser@gmail.com";
        String phoneNumber = "0961234567";
        String confirmPassword = "12345678";

        given(service.isConfirmPasswordEqualPassword(password, confirmPassword)).willReturn(true);
        given(service.isValidUsername(username)).willReturn(true);
        given(service.isDuplicatedUsername(username)).willReturn(false);
        given(service.isValidEmail(email)).willReturn(true);
        given(service.isDuplicatedEmail(email)).willReturn(false);
        given(service.isValidPhoneNumber(phoneNumber)).willReturn(true);

        mockMvc.perform(post("/registration")
                    .param("username", username)
                    .param("password", password)
                    .param("fullName", fullName)
                    .param("email", email)
                    .param("phoneNumber", phoneNumber)
                    .param("confirmPassword", confirmPassword))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("success"))
                .andExpect(view().name(LOGIN_PAGE));

        then(service).should().save(any(AccountRegistrationDTO.class));
    }

    @Test
    void givenAllFieldsIsValidExceptUsername_whenAccessRegistrationPageByPost_thenReturnRegistrationPage() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(false);
        String username = "a b";
        String password = "12345678";
        String fullName = "Invalid User";
        String email = "validUser@gmail.com";
        String phoneNumber = "0961234567";
        String confirmPassword = "12345678";

        given(service.isConfirmPasswordEqualPassword(password, confirmPassword)).willReturn(true);
        given(service.isValidUsername(username)).willReturn(false);
        given(service.isDuplicatedUsername(username)).willReturn(false);
        given(service.isValidEmail(email)).willReturn(true);
        given(service.isDuplicatedEmail(email)).willReturn(false);
        given(service.isValidPhoneNumber(phoneNumber)).willReturn(true);

        mockMvc.perform(post("/registration")
                        .param("username", username)
                        .param("password", password)
                        .param("fullName", fullName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("confirmPassword", confirmPassword))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("account"))
                .andExpect(model().attributeHasFieldErrors("account", "username"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name(REGISTRATION_PAGE));
    }

    @Test
    void givenAllFieldsIsValidExceptUsernameIsDuplicated_whenAccessRegistrationPageByPost_thenReturnRegistrationPage() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(false);
        String username = "duplicate";
        String password = "12345678";
        String fullName = "Valid User";
        String email = "validUser@gmail.com";
        String phoneNumber = "0961234567";
        String confirmPassword = "12345678";

        given(service.isConfirmPasswordEqualPassword(password, confirmPassword)).willReturn(true);
        given(service.isValidUsername(username)).willReturn(true);
        given(service.isDuplicatedUsername(username)).willReturn(true);
        given(service.isValidEmail(email)).willReturn(true);
        given(service.isDuplicatedEmail(email)).willReturn(false);
        given(service.isValidPhoneNumber(phoneNumber)).willReturn(true);

        mockMvc.perform(post("/registration")
                        .param("username", username)
                        .param("password", password)
                        .param("fullName", fullName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("confirmPassword", confirmPassword))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("account"))
                .andExpect(model().attributeHasFieldErrors("account", "username"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name(REGISTRATION_PAGE));
    }

    @Test
    void givenAllFieldsIsValidExceptConfirmPassword_whenAccessRegistrationPageByPost_thenReturnRegistrationPage() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(false);
        String username = "valid";
        String password = "12345678";
        String fullName = "Valid User";
        String email = "validUser@gmail.com";
        String phoneNumber = "0961234567";
        String confirmPassword = "1234567";

        given(service.isConfirmPasswordEqualPassword(password, confirmPassword)).willReturn(false);
        given(service.isValidUsername(username)).willReturn(true);
        given(service.isDuplicatedUsername(username)).willReturn(false);
        given(service.isValidEmail(email)).willReturn(true);
        given(service.isDuplicatedEmail(email)).willReturn(false);
        given(service.isValidPhoneNumber(phoneNumber)).willReturn(true);

        mockMvc.perform(post("/registration")
                        .param("username", username)
                        .param("password", password)
                        .param("fullName", fullName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("confirmPassword", confirmPassword))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("account"))
                .andExpect(model().attributeHasFieldErrors("account", "confirmPassword"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name(REGISTRATION_PAGE));
    }

    @Test
    void givenAllFieldsIsValidExceptEmail_whenAccessRegistrationPageByPost_thenReturnRegistrationPage() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(false);
        String username = "valid";
        String password = "12345678";
        String fullName = "Valid User";
        String email = "validUser@gmail";
        String phoneNumber = "0961234567";
        String confirmPassword = "12345678";

        given(service.isConfirmPasswordEqualPassword(password, confirmPassword)).willReturn(true);
        given(service.isValidUsername(username)).willReturn(true);
        given(service.isDuplicatedUsername(username)).willReturn(false);
        given(service.isValidEmail(email)).willReturn(false);
        given(service.isDuplicatedEmail(email)).willReturn(false);
        given(service.isValidPhoneNumber(phoneNumber)).willReturn(true);

        mockMvc.perform(post("/registration")
                        .param("username", username)
                        .param("password", password)
                        .param("fullName", fullName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("confirmPassword", confirmPassword))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("account"))
                .andExpect(model().attributeHasFieldErrors("account", "email"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name(REGISTRATION_PAGE));
    }

    @Test
    void givenAllFieldsIsValidExceptEmailIsDuplicated_whenAccessRegistrationPageByPost_thenReturnRegistrationPage() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(false);
        String username = "valid";
        String password = "12345678";
        String fullName = "Valid User";
        String email = "duplicate@gmail.com";
        String phoneNumber = "0961234567";
        String confirmPassword = "12345678";

        given(service.isConfirmPasswordEqualPassword(password, confirmPassword)).willReturn(true);
        given(service.isValidUsername(username)).willReturn(true);
        given(service.isDuplicatedUsername(username)).willReturn(false);
        given(service.isValidEmail(email)).willReturn(true);
        given(service.isDuplicatedEmail(email)).willReturn(true);
        given(service.isValidPhoneNumber(phoneNumber)).willReturn(true);

        mockMvc.perform(post("/registration")
                        .param("username", username)
                        .param("password", password)
                        .param("fullName", fullName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("confirmPassword", confirmPassword))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("account"))
                .andExpect(model().attributeHasFieldErrors("account", "email"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name(REGISTRATION_PAGE));
    }

    @Test
    void givenAllFieldsIsValidExceptPhoneNumber_whenAccessRegistrationPageByPost_thenReturnRegistrationPage() throws Exception {
        utilsMock.when(AuthenticationCheckingUtils::isAuthenticated).thenReturn(false);
        String username = "valid";
        String password = "12345678";
        String fullName = "Valid User";
        String email = "validUser@gmail.com";
        String phoneNumber = "123455";
        String confirmPassword = "12345678";

        given(service.isConfirmPasswordEqualPassword(password, confirmPassword)).willReturn(true);
        given(service.isValidUsername(username)).willReturn(true);
        given(service.isDuplicatedUsername(username)).willReturn(false);
        given(service.isValidEmail(email)).willReturn(true);
        given(service.isDuplicatedEmail(email)).willReturn(false);
        given(service.isValidPhoneNumber(phoneNumber)).willReturn(false);

        mockMvc.perform(post("/registration")
                        .param("username", username)
                        .param("password", password)
                        .param("fullName", fullName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("confirmPassword", confirmPassword))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("account"))
                .andExpect(model().attributeHasFieldErrors("account", "phoneNumber"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name(REGISTRATION_PAGE));
    }
}