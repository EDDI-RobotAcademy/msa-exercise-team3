package com.example.account.repository;

import com.example.account.entity.AccountLoginType;
import com.example.account.entity.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountLoginTypeRepository extends JpaRepository<AccountLoginType, Long> {
    Optional<AccountLoginType> findByLoginType(LoginType loginType);
}
