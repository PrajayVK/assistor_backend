package com.assistor.backend.validation;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );

    public boolean validEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
