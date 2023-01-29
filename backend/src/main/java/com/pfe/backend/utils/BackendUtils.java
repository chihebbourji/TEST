package com.pfe.backend.utils;

public class BackendUtils {
    private BackendUtils() {
    }
    public static boolean isEmptyOrNull(String value){
        return value == null || value.isEmpty();
    }
}
