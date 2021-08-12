package com.internship.service.impl;

import com.internship.dto.MyUserDetails;
import com.internship.model.Account;
import com.internship.model.Role;
import com.internship.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException("Tên đăng nhập hoặc mật khẩu không đúng");
        }

        return new MyUserDetails(account);
    }
}
