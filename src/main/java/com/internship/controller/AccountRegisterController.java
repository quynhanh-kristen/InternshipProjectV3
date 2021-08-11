package com.internship.controller;

import com.internship.dto.AccountRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class AccountRegisterController {

    @Autowired
    private com.internship.service.IAccountService IAccountService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor((true));
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //Thymeleaf will get the account object to bind data to registration form
    @ModelAttribute("account")
    public AccountRegistrationDTO accountRegistrationDTO() {
        return new AccountRegistrationDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registrationPage";
    }

    @PostMapping
    public String registrationProcess(@Valid @ModelAttribute("account") AccountRegistrationDTO registrationDTO,
                                      BindingResult bindingResult, Model model) {

        boolean isError = false;

        //check if password equals confirm password
        String password = registrationDTO.getPassword();
        String confirmPassword = registrationDTO.getConfirmPassword();

        if(password != null && confirmPassword != null) {
            if(!IAccountService.isConfirmPasswordEqualPassword(password, confirmPassword)) {
                bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Mật khẩu xác nhận không khớp");
                isError = true;
            }
        }

        //validate username
        String username = registrationDTO.getUsername();
        if(username != null) {
            //check if username contains any special character
            if(!IAccountService.isValidUsername(username)) {
                bindingResult.rejectValue("username", "error.username", "Tên đăng nhập không được chứa ký tự đặc biệt");
                isError = true;
            }

            //check if username exists
            if(IAccountService.isDuplicatedUsername(username)){
                bindingResult.rejectValue("username", "error.username", "Tên đăng nhập đã tồn tại");
                isError = true;
            }
        }

        //validate email
        String email = registrationDTO.getEmail();
        if(email != null) {
            if(!IAccountService.isValidEmail(email)) {
                if(IAccountService.isDuplicatedEmail(email)) {
                    bindingResult.rejectValue("email", "error.email", "Email không hợp lệ");
                    isError = true;
                }
            }

            if(IAccountService.isDuplicatedEmail(email)) {
                bindingResult.rejectValue("email", "error.email", "Email đã tồn tại");
                isError = true;
            }
        }

        //validate phone number
        String phoneNumber = registrationDTO.getPhoneNumber();
        if(phoneNumber != null) {
            if(!IAccountService.isValidPhoneNumber(phoneNumber)) {
                bindingResult.rejectValue("phoneNumber", "error.phoneNumber", "Số điện thoại không hợp lệ");
                isError = true;
            }
        }

        if(isError || bindingResult.hasErrors()) {
            return "registrationPage";
        }

        IAccountService.save(registrationDTO);

        model.addAttribute("success", "Bạn đã đăng ký thành công");
        return "loginPage";
    }
}
