package br.com.jeferson.password.v1.rule;

import java.util.regex.Pattern;

public class ContainsUppercaseRule implements PasswordRule {
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");

    public boolean validate(String password) {
        return password != null && UPPERCASE_PATTERN.matcher(password).find();
    }

    public String message() {
        return "Password must contain at least one uppercase letter.";
    }
}