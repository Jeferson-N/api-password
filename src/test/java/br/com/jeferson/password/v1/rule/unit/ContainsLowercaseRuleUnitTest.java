package br.com.jeferson.password.v1.rule.unit;

import org.junit.jupiter.api.Test;

import br.com.jeferson.password.v1.rule.ContainsLowercaseRule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class ContainsLowercaseRuleUnitTest {

    @Test
    @DisplayName("should return true when password contains a lowercase letter")
    void shouldReturnTrue_whenPasswordContainsLowercase() {
        ContainsLowercaseRule rule = new ContainsLowercaseRule();
        assertTrue(rule.validate("Password123"));
        assertTrue(rule.validate("lowercase"));
        assertTrue(rule.validate("123abc"));
    }

    @Test
    @DisplayName("should return false when password does not contain lowercase")
    void shouldReturnFalse_whenPasswordDoesNotContainLowercase() {
        ContainsLowercaseRule rule = new ContainsLowercaseRule();
        assertFalse(rule.validate("PASSWORD123"));
        assertFalse(rule.validate("123456"));
        assertFalse(rule.validate("!@#$%^&*()"));
    }

    @Test
    @DisplayName("should return false when password is empty")
    void shouldReturnFalse_whenPasswordIsEmpty() {
        ContainsLowercaseRule rule = new ContainsLowercaseRule();
        assertFalse(rule.validate(""));
    }

    @Test
    @DisplayName("should return false when password is null")
    void shouldReturnFalse_whenPasswordIsNull() {
        ContainsLowercaseRule rule = new ContainsLowercaseRule();
        assertFalse(rule.validate(null));
    }

    @Test
    @DisplayName("should return correct message")
    void shouldReturnCorrectMessage() {
        ContainsLowercaseRule rule = new ContainsLowercaseRule();
        assertEquals("Password must contain at least one lowercase letter.", rule.message());
    }
}