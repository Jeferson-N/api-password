package br.com.jeferson.password.v1.rule.unit;

import org.junit.jupiter.api.Test;

import br.com.jeferson.password.v1.rule.ContainsSpecialCharRule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class ContainsSpecialCharRuleUnitTest {

    @Test
    @DisplayName("should return true when password contains a special character")
    void shouldReturnTrue_whenPasswordContainsSpecialChar() {
        ContainsSpecialCharRule rule = new ContainsSpecialCharRule("!@#$%");
        assertTrue(rule.validate("password!"));
        assertTrue(rule.validate("pass@word"));
        assertTrue(rule.validate("pass#word"));
    }

    @Test
    @DisplayName("should return false when password does not contain a special character")
    void shouldReturnFalse_whenPasswordDoesNotContainSpecialChar() {
        ContainsSpecialCharRule rule = new ContainsSpecialCharRule("!@#$%");
        assertFalse(rule.validate("password"));
        assertFalse(rule.validate("pass123word"));
        assertFalse(rule.validate("PASSword"));
    }

    @Test
    @DisplayName("should return false when password is empty")
    void shouldReturnFalse_whenPasswordIsEmpty() {
        ContainsSpecialCharRule rule = new ContainsSpecialCharRule("!@#$%");
        assertFalse(rule.validate(""));
    }

    @Test
    @DisplayName("should return false when password is null")
    void shouldReturnFalse_whenPasswordIsNull() {
        ContainsSpecialCharRule rule = new ContainsSpecialCharRule("!@#$%");
        assertFalse(rule.validate(null));
    }

    @Test
    @DisplayName("should return correct message")
    void shouldReturnCorrectMessage() {
        ContainsSpecialCharRule rule = new ContainsSpecialCharRule("!@#$%");
        assertEquals("Password must contain at least one special character (!@#$%).", rule.message());
    }

    @Test
    @DisplayName("should return true when password contains a special character from custom set")
    void shouldReturnTrue_whenPasswordContainsCustomSpecialChar() {
        ContainsSpecialCharRule rule = new ContainsSpecialCharRule("*&^");
        assertTrue(rule.validate("password*"));
        assertTrue(rule.validate("pass&word"));
        assertTrue(rule.validate("pass^word"));
    }
}