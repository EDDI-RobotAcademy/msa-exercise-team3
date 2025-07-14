package com.example.account.controller.request;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginAccountRequest {
    private String userId;
    private String password;
}
