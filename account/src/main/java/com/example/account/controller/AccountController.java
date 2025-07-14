package com.example.account.controller;

import com.example.account.controller.response.LoginAccountResponse;
import com.example.account.controller.request.LoginAccountRequest;
import com.example.account.entity.Account;
import com.example.account.redis_cache.RedisCacheService;
import com.example.account.repository.AccountRepository;
import com.example.account.utility.EncryptionUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RedisCacheService redisCacheService;

    @PostMapping("/login")
    public LoginAccountResponse login(@RequestBody LoginAccountRequest request){
        log.info("login -> LoginRequest : {}", request);
        String requestUserId = request.getUserId();
        Optional<Account> maybeAccount = accountRepository.findByUserId(requestUserId);

        if(maybeAccount.isEmpty()){
            return new LoginAccountResponse("아이디와 비밀번호가 틀렸습니다.");
        }
        Account account = maybeAccount.get();
        String requestPassword = request.getPassword();

        String nickName = account.getNickName();
        String message = nickName + "으로 로그인 성공하셨습니다!";

        boolean matched = EncryptionUtility.matches(requestPassword, account.getPassword());
        if(!matched){
            return new LoginAccountResponse("아이디와 비밀번호가 틀렸습니다.");
        }

        String token = UUID.randomUUID().toString();
        redisCacheService.setKeyAndValue(token, account.getId(), Duration.ofDays(1));
        return LoginAccountResponse.from(message,token);
    }


}
