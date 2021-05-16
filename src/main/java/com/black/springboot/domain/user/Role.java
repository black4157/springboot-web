package com.black.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"),      //항상 권한코드가 앞에 있어야 함
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
