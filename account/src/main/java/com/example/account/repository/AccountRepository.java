package com.example.account.repository;

import com.example.account.entity.Account;
import com.example.account.entity.AccountLoginType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByEmailAndLoginType(String requestEmail, AccountLoginType type);
}
