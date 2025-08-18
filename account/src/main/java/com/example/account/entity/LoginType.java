package com.example.account.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum LoginType {
    LOCAL,
    KAKAO,
    GOOGLE,
    NAVER;

    @JsonCreator
    public static LoginType from(String input) {
        return Arrays.stream(values())
                .filter(e -> e.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Unknown loginType: " + input));
    }
}
