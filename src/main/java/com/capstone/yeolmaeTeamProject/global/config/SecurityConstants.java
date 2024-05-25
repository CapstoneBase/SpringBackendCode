package com.capstone.yeolmaeTeamProject.global.config;

public class SecurityConstants {

    public static final String[] PERMIT_ALL = {
            "/static/**",
            "/resources/files/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",
            "/error",
            "/"
    };

    public static final String[] PERMIT_ALL_API_ENDPOINTS_GET = {
            "/api/v1/posts"
    };

    public static final String[] PERMIT_ALL_API_ENDPOINTS_POST = {
            "/api/v1/login",
            "/api/v1/login/reissue",
            "/api/v1/users",
            "/api/v1/users/check"
    };
}
