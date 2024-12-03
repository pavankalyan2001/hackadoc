package com.jmeter.performance.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String loginMsg;

    public LoginResponse(String loginMsg) {
        this.loginMsg = loginMsg;
    }
}
