package com.example.account.controller;

import com.example.account.controller.request.RegisterAccountRequest;
import com.example.account.controller.response.IdAccountResponse;
import com.example.account.controller.response.LoginAccountResponse;
import com.example.account.controller.request.LoginAccountRequest;
import com.example.account.controller.response.RegisterAccountResponse;
import com.example.account.entity.Account;
import com.example.account.redis_cache.RedisCacheService;
import com.example.account.repository.AccountRepository;
import com.example.account.utility.EncryptionUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public RegisterAccountResponse register(@RequestBody RegisterAccountRequest request){
        log.info("register -> RegisterAccountRequest: {}", request);
        Account registerAccount = request.toAccount();
        Account createdAccount = accountRepository.save(registerAccount);

        return RegisterAccountResponse.from(createdAccount);
    }

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
        return LoginAccountResponse.from(token, message);
    }
    @GetMapping("/find-id")
    public ResponseEntity<IdAccountResponse> getAccountId(@RequestHeader("Authorization") String token){
        String pureToken = extractToken(token);
        String accountId = redisCacheService.getValueByKey(pureToken, String.class);

        if(accountId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Account account = accountRepository.findById(Long.parseLong(accountId))
                .orElseThrow(()-> new RuntimeException("사용자가 존재하지 않음"));

        return ResponseEntity.ok(new IdAccountResponse(account.getId()));
    }

    private String extractToken(String token){
        if(token != null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return token;
    }


}
