package com.yueking.core.shiro.service;

import com.yueking.core.shiro.model.LoginResult;

public interface LoginService {
    LoginResult login(String username, String password);
    void logout();
}
