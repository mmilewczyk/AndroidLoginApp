package com.milewczyk.wsb_51033_project;

import java.util.regex.Pattern;

public class ValidationService {

    public static boolean validateEmail(String email) {
        String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        Pattern EMAIL_REGEX = Pattern.compile(EMAIL_VERIFICATION);
        return EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean validatePassword(String password) {
        String PASSWORD_VERIFICATION = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
        Pattern EMAIL_REGEX = Pattern.compile(PASSWORD_VERIFICATION);
        return EMAIL_REGEX.matcher(password).matches();
    }
}
