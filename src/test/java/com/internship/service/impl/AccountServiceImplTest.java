package com.internship.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl service;

    @ParameterizedTest
    @ValueSource(strings = {"ageha", "AGEHA", "Ageha", "ag3ha", "AG3HA", "Ag3ha"})
    void givenValidUsername_thenReturnTrue(String username) {
        assertThat(service.isValidUsername(username)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ageha$", "ageha#@", "#$@#%@", "age ha", "   "})
    void givenInvalidUsername_thenReturnFalse(String username) {
        assertThat(service.isValidUsername(username)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a@gma.co", "abcde@gmail.com", "abcd@aloha.comz"})
    void givenValidEmail_thenReturnTrue(String email) {
        assertThat(service.isValidEmail(email)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcde", "abcde.com", "abcde@fal", "abcde@fa.c", "abcde@aloa.comzz", "@laoj.com", ".com"})
    void givenInvalidEmail_thenReturnFalse(String email) {
        assertThat(service.isValidEmail(email)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0351234567", "0561234567", "0781234567", "0881234567", "0991234566"})
    void givenValidPhoneNumber_thenReturnTrue(String phoneNum) {
        assertThat(service.isValidPhoneNumber(phoneNum)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"8888888888", "-15166", "*555656", "c2p788/", "     ", "07878945"})
    void givenInvalidPhoneNumber_thenReturnFalse(String phoneNum) {
        assertThat(service.isValidPhoneNumber(phoneNum)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"agehachou1234, agehachou1234", "ageha@#@, ageha@#@"})
    void givenConfirmPasswordEqualsPassword_thenReturnTrue(String pwd, String confirmPwd) {
        assertThat(service.isConfirmPasswordEqualPassword(pwd, confirmPwd)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"Agehachou1234, agehachou1234", "Aloha$%$, aloha342"})
    void givenConfirmPasswordNotEqualsPassword_thenReturnTrue(String pwd, String confirmPwd) {
        assertThat(service.isConfirmPasswordEqualPassword(pwd, confirmPwd)).isFalse();
    }
}