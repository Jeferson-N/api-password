package br.com.jeferson.password.v1.rule.unit;

import org.junit.jupiter.api.Test;

import br.com.jeferson.password.v1.rule.MinLengthRule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class MinLengthRuleUnitTest {

    @Test
    @DisplayName("should return true when password meets minimum length")
    void shouldReturnTrue_whenPasswordMeetsMinLength() {
        MinLengthRule rule = new MinLengthRule(8);
        assertTrue(rule.validate("password123"));
    }

    @Test
    @DisplayName("should return false when password does not meet minimum length")
    void shouldReturnFalse_whenPasswordDoesNotMeetMinLength() {
        MinLengthRule rule = new MinLengthRule(8);
        assertFalse(rule.validate("short"));
    }

    @Test
    @DisplayName("should return correct message")
    void shouldReturnCorrectMessage() {
        MinLengthRule rule = new MinLengthRule(8);
        assertEquals("Password must be at least 8 characters long.", rule.message());
    }
}