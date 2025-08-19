package com.example.account.controller.request;

import com.example.account.entity.Account;
import com.example.account.entity.AccountLoginType;
import com.example.account.utility.EncryptionUtility;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAccountRequest {
    private String email;
    private String password;
    private String nickName;

    public Account toAccount(AccountLoginType loginType) {
        return new Account(email, EncryptionUtility.encode(password), nickName, loginType);
    }
}
