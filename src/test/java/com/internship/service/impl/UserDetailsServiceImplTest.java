package com.internship.service.impl;

import com.internship.InternshipProjectV3.post.impl.UserDetailsServiceImpl;
import com.internship.dto.MyUserDetails;
import com.internship.model.Account;
import com.internship.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    UserDetailsServiceImpl service;

    @Test
    void givenValidUsername_whenAccountNotNull_thenReturnMyUserDetail() {
        String validUsername = "validUsername";

        given(accountRepository.findByUsername(validUsername))
                .willReturn(new Account());

        MyUserDetails returnedValue = (MyUserDetails) service.loadUserByUsername(validUsername);

        assertThat(returnedValue).isNotNull();
        then(accountRepository).should().findByUsername(anyString());
    }

    @Test
    void giveInvalidUsername_whenAccountNull_thenReturnException() {
        String invalidUsername = "invalidUsername";

        given(accountRepository.findByUsername(invalidUsername))
                .willThrow(new UsernameNotFoundException("Tên đăng nhập hoặc mật khẩu không đúng"));

        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(invalidUsername));
        then(accountRepository).should().findByUsername(anyString());
    }
}