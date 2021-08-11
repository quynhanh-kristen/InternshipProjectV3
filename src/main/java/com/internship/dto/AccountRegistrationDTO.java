package com.internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegistrationDTO {
    @NotNull(message = "Hãy nhập tên đăng nhập")
    @Size(min = 3, max = 15, message = "Tên đăng nhập từ 3 đến 15 kỳ tự")
    private String username;

    @NotNull(message = "Hãy nhập mật khẩu")
    @Min(value = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    private String password;

    @NotNull(message = "Hãy nhập họ tên")
    @Size(max = 50, message = "Họ tên tối đa 50 ký tự")
    private String fullName;

    @NotNull(message = "Hãy nhập email")
    @Size(max = 320, message = "Email tối đa 320 ký tự")
    private String email;

    @NotNull(message = "Hãy nhập số điện thoại")
    @Size(max = 10, message = "Số điện thoại tối đa 10 ký tự")
    private String phoneNumber;

    @NotNull(message = "Hãy nhập xác nhận mật khẩu")
    private String confirmPassword;
}
