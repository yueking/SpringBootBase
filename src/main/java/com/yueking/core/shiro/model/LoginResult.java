package com.yueking.core.shiro.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResult implements Serializable {
    private boolean login = false;
    private String result;
}
