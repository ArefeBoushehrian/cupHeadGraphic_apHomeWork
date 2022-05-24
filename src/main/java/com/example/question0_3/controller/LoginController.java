package com.example.question0_3.controller;

import com.example.question0_3.Enum.LoginMessages;
import com.example.question0_3.model.User;

public class LoginController {
    public LoginMessages checkInputValidationCreate(String username, String password) {
        if (User.isUsernameRepeated(username)) return LoginMessages.REPEATED_USERNAME;
        return LoginMessages.SUCCESS;
    }

    public LoginMessages checkInputValidationLogin(String username, String password) {
        if (User.isUsernameAndPasswordValid(username, password)) return LoginMessages.SUCCESS;
        return LoginMessages.INCORRECT_INFORMATION;
    }
}
