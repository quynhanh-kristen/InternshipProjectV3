package com.internship.service.impl;

import com.internship.dto.AccountRegistrationDTO;
import com.internship.model.Account;
import com.internship.model.Role;
import com.internship.repository.AccountRepository;
import com.internship.repository.RoleRepository;
import com.internship.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(AccountRegistrationDTO accountDTO) {
        Date createdDate = new Date();
        boolean isBanned = false;
        Role role = roleRepository.getById(RoleServiceImpl.UPLOADER);

        Account account = new Account(
                accountDTO.getUsername(),
                passwordEncoder.encode(accountDTO.getPassword()),
                accountDTO.getFullName(),
                accountDTO.getEmail(),
                accountDTO.getPhoneNumber(),
                createdDate,
                isBanned,
                role
        );
        return accountRepository.save(account);
    }

    @Override
    public Boolean isDuplicatedUsername(String username) {
        return accountRepository.findByUsername(username) != null;
    }

    @Override
    public Boolean isValidUsername(String username) {
        return username.matches("^\\S*$");
    }

    @Override
    public Boolean isDuplicatedEmail(String email) {
        return accountRepository.findByEmail(email) != null;
    }

    @Override
    public Boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    @Override
    public Boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
    }

    @Override
    public Boolean isConfirmPasswordEqualPassword(String password, String confirmPassword) {
        return confirmPassword.equalsIgnoreCase(password);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
