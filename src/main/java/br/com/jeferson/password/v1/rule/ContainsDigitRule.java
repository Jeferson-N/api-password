package br.com.jeferson.password.v1.rule;

import java.util.regex.Pattern;

public class ContainsDigitRule implements PasswordRule {
    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");

    public boolean validate(String password) {
        return password != null && DIGIT_PATTERN.matcher(password).find();
    }

    public String message() {
        return "Password must contain at least one digit.";
    }
}