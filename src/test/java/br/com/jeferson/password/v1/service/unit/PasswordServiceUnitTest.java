package br.com.jeferson.password.v1.service.unit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import br.com.jeferson.password.v1.dto.PasswordResponseDto;
import br.com.jeferson.password.v1.service.PasswordService;

class PasswordServiceUnitTest {

    private PasswordService passwordService;

    @BeforeEach
    void setUp() {
        passwordService = new PasswordService();
    }

    @Test
    @DisplayName("should return true, when valid password")
    void shouldReturnTrue_whenValidPassword() {
        String validPassword = "Abc@12345";
        PasswordResponseDto response = passwordService.validate(validPassword);

        assertTrue(response.isValid());
        assertTrue(response.getErrors().isEmpty());
    }

    @Test
    @DisplayName("should contain a character quantity message, when not in minimum size")
    void shouldContainCharacterCountMessage_whenNotInMinimumSize() {
        String shortPassword = "Ab1@";
        PasswordResponseDto response = passwordService.validate(shortPassword);

        assertFalse(response.isValid());
        assertTrue(response.getErrors().contains("Password must be at least 9 characters long."));
    }

    @Test
    @DisplayName("should return missing digit message when it does not contain digit")
    void shouldReturnMissingDigitMessage_whenPasswordDoesNotContainDigit() {
        String noDigitPassword = "Abcdef@gh";
        PasswordResponseDto response = passwordService.validate(noDigitPassword);

        assertFalse(response.isValid());
        assertTrue(response.getErrors().contains("Password must contain at least one digit."));
    }

    @Test
    @DisplayName("should return missing lowercase message when it does not contain lowercase")
    void shouldReturnMissingLowercaseMessage_whenPasswordDoesNotContainLowercase() {
        String noLowercasePassword = "ABC@12345";
        PasswordResponseDto response = passwordService.validate(noLowercasePassword);

        assertFalse(response.isValid());
        assertTrue(response.getErrors().contains("Password must contain at least one lowercase letter."));
    }

    @Test
    @DisplayName("should return missing uppercase message when it does not contain uppercase")
    void shouldReturnMissingUppercaseMessage_whenPasswordDoesNotContainUppercase() {
        String noUppercasePassword = "abc@12345";
        PasswordResponseDto response = passwordService.validate(noUppercasePassword);

        assertFalse(response.isValid());
        assertTrue(response.getErrors().contains("Password must contain at least one uppercase letter."));
    }

    @Test
    @DisplayName("should return missing special character message when it does not contain special character")
    void shouldReturnMissingSpecialCharacterMessage_whenPasswordDoesNotContainSpecialCharacter() {
        String noSpecialCharPassword = "Abc123456";
        PasswordResponseDto response = passwordService.validate(noSpecialCharPassword);

        assertFalse(response.isValid());
        assertTrue(response.getErrors()
                .contains("Password must contain at least one special character (!@#$%^&*()-+)."));
    }

    @Test
    @DisplayName("should return repeated character message when it contains repeated characters")
    void shouldReturnRepeatedCharacterMessage_whenPasswordDoesNotContainRepeatedCharacters() {
        String repeatedCharPassword = "Abc@12345A";
        PasswordResponseDto response = passwordService.validate(repeatedCharPassword);

        assertFalse(response.isValid());
        assertTrue(response.getErrors().contains("Password must not contain repeated characters."));
    }

    @Test
    @DisplayName("should return multiple error messages when password violates multiple rules")
    void shouldReturnMultipleErrorMessages_whenPasswordViolatesMultipleRules() {
        String invalidPassword = "abc123";
        PasswordResponseDto response = passwordService.validate(invalidPassword);

        assertFalse(response.isValid());
        assertTrue(response.getErrors().contains("Password must be at least 9 characters long."));
        assertTrue(response.getErrors().contains("Password must contain at least one uppercase letter."));
        assertTrue(
                response.getErrors().contains("Password must contain at least one special character (!@#$%^&*()-+)."));
    }
}