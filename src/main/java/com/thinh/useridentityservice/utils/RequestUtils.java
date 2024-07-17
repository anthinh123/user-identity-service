package com.thinh.useridentityservice.utils;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String getTokenFromRequest(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }
}
