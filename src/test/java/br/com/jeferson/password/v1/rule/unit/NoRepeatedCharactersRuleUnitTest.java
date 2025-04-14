package br.com.jeferson.password.v1.rule.unit;

import org.junit.jupiter.api.Test;

import br.com.jeferson.password.v1.rule.NoRepeatedCharactersRule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class NoRepeatedCharactersRuleUnitTest {

    @Test
    @DisplayName("should return true when password contains unique characters")
    void shouldReturnTrue_whenPasswordContainsUniqueCharacters() {
        NoRepeatedCharactersRule rule = new NoRepeatedCharactersRule();
        assertTrue(rule.validate("abcdef"), "Password with unique characters should be valid.");
    }

    @Test
    @DisplayName("should return false when password contains repeated characters")
    void shouldReturnFalse_whenPasswordContainsRepeatedCharacters() {
        NoRepeatedCharactersRule rule = new NoRepeatedCharactersRule();
        assertFalse(rule.validate("abcdeff"));
    }

    @Test
    @DisplayName("should return false when password is null")
    void shouldReturnFalse_whenPasswordIsNull() {
        NoRepeatedCharactersRule rule = new NoRepeatedCharactersRule();
        assertFalse(rule.validate(null));
    }

    @Test
    @DisplayName("return message should match the expected output.")

    void shouldReturnTheMessageAccordingToTheExpectedOutput() {
        NoRepeatedCharactersRule rule = new NoRepeatedCharactersRule();
        assertEquals("Password must not contain repeated characters.", rule.message());
    }
}