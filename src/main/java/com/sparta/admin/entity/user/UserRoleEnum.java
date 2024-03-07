package com.sparta.admin.entity.user;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

    MANAGER(Authority.MANAGER),
    STAFF(Authority.STAFF);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}