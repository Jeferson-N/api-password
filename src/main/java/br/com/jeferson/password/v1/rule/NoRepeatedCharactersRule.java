package br.com.jeferson.password.v1.rule;

import java.util.Set;
import java.util.HashSet;

public class NoRepeatedCharactersRule implements PasswordRule {

    public boolean validate(String password) {
        if (password == null) {
            return false;
        }

        Set<Character> characters = new HashSet<>();
        for (char character : password.toCharArray()) {
            if (!characters.add(character)) {
                return false;
            }
        }
        return true;
    }

    public String message() {
        return "Password must not contain repeated characters.";
    }
}