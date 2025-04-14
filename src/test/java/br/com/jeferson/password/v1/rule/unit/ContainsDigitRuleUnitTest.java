package br.com.jeferson.password.v1.rule.unit;

import org.junit.jupiter.api.Test;

import br.com.jeferson.password.v1.rule.ContainsDigitRule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class ContainsDigitRuleUnitTest {

    @Test
    @DisplayName("should return true when password contains a digit")
    void shouldReturnTrue_whenPasswordContainsDigit() {
        ContainsDigitRule rule = new ContainsDigitRule();
        assertTrue(rule.validate("password1"));
        assertTrue(rule.validate("12345"));
        assertTrue(rule.validate("a1b2c3"));
    }

    @Test
    @DisplayName("should return false when password does not contain a digit")
    void shouldReturnFalse_whenPasswordDoesNotContainDigit() {
        ContainsDigitRule rule = new ContainsDigitRule();
        assertFalse(rule.validate("password"));
        assertFalse(rule.validate("abcdef"));
        assertFalse(rule.validate("!@#$%^&*"));
    }

    @Test
    @DisplayName("should return correct message")
    void shouldReturnCorrectMessage() {
        ContainsDigitRule rule = new ContainsDigitRule();
        assertEquals("Password must contain at least one digit.", rule.message());
    }
}