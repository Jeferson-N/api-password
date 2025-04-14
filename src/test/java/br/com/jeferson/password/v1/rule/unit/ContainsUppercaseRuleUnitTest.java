package br.com.jeferson.password.v1.rule.unit;

import org.junit.jupiter.api.Test;

import br.com.jeferson.password.v1.rule.ContainsUppercaseRule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class ContainsUppercaseRuleUnitTest {

    @Test
    @DisplayName("should return true when password contains an uppercase letter")
    void shouldReturnTrue_whenPasswordContainsUppercase() {
        ContainsUppercaseRule rule = new ContainsUppercaseRule();
        assertTrue(rule.validate("Password123"));
    }

    @Test
    @DisplayName("should return false when password does not contain uppercase")
    void shouldReturnFalse_whenPasswordDoesNotContainUppercase() {
        ContainsUppercaseRule rule = new ContainsUppercaseRule();
        assertFalse(rule.validate("password123"));
    }

    @Test
    @DisplayName("should return false when password contains only digits")
    void shouldReturnFalse_whenPasswordIsEmpty() {
        ContainsUppercaseRule rule = new ContainsUppercaseRule();
        assertFalse(rule.validate(""));
    }

    @Test
    @DisplayName("should return false when password is null")
    void shouldReturnFalse_whenPasswordIsNull() {
        ContainsUppercaseRule rule = new ContainsUppercaseRule();
        assertFalse(rule.validate(null));
    }

    @Test
    @DisplayName("should return correct message")
    void shouldReturnCorrectMessage() {
        ContainsUppercaseRule rule = new ContainsUppercaseRule();
        assertEquals("Password must contain at least one uppercase letter.", rule.message());
    }
}