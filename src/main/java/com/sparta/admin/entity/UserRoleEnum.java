package com.sparta.admin.entity;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

    MANAGER(Authority.ADMIN),  // 관리자 권한
    STAFF(Authority.USER);  // 사용자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }
}