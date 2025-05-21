package com.picverse.util;

import java.util.regex.Pattern;

public class ValidationUtil {
	
	// Checks if the provided string is null or empty
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    // Checks if the provided string contains only digits
    public static boolean isAlphabetic(String value) {
        return value != null && value.matches("^[a-zA-Z]+$");
    }
    
    // Checks if the provided string contains only digits
    public static boolean isAlphanumericStartingWithLetter(String value) {
        return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }
    
    // Checks if the provided string is a valid email address
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && Pattern.matches(emailRegex, email);
    }   
    
    // Checks if the provided string is a valid full name (at least 2 words)
    public static boolean isValidFullName(String value) {
        return value != null && value.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)+$");
    }
    
    // Check if the provided string is a valid password (at least 4 characters)
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^.{4,}$";
        return password != null && password.matches(passwordRegex);
    }

}
