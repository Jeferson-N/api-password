package br.com.jeferson.password.v1.rule;

import java.util.regex.Pattern;

public class ContainsSpecialCharRule implements PasswordRule {
    private final String specialChars;
    private final Pattern specialCharPattern;

    public ContainsSpecialCharRule(String specialChars) {
        this.specialChars = specialChars;
        this.specialCharPattern = Pattern.compile("[" + Pattern.quote(specialChars) + "]");
    }

    public boolean validate(String password) {
        return password != null && specialCharPattern.matcher(password).find();
    }

    public String message() {
        return String.format("Password must contain at least one special character (%s).", specialChars);
    }
}
