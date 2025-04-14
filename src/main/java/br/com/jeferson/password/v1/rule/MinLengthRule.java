package br.com.jeferson.password.v1.rule;

public class MinLengthRule implements PasswordRule {
    private final int minLength;

    public MinLengthRule(int minLength) {
        this.minLength = minLength;
    }

    public boolean validate(String password) {
        return password != null && password.length() >= minLength;
    }

    public String message() {
        return String.format("Password must be at least %d characters long.", minLength);
    }
}