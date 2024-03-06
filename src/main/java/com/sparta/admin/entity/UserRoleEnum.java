package com.sparta.admin.entity;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

    MANAGER(Authority.MANAGER),  // 관리자 권한
    STAFF(Authority.STAFF);  // 사용자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}