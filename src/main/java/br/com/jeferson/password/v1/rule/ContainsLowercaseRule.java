package br.com.jeferson.password.v1.rule;

import java.util.regex.Pattern;

public class ContainsLowercaseRule implements PasswordRule {
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");

    public boolean validate(String password) {
        return password != null && LOWERCASE_PATTERN.matcher(password).find();
    }

    public String message() {
        return "Password must contain at least one lowercase letter.";
    }
}