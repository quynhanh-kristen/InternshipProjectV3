package com.internship.service;

import com.internship.dto.AccountRegistrationDTO;
import com.internship.model.Account;

public interface IAccountService {
    Account save(AccountRegistrationDTO accountDTO);
    Boolean isDuplicatedUsername(String username);
    Boolean isValidUsername(String username);
    Boolean isDuplicatedEmail(String email);
    Boolean isValidEmail(String email);
    Boolean isValidPhoneNumber(String phoneNumber);
    Boolean isConfirmPasswordEqualPassword(String password, String confirmPassword);
}
