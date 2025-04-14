package br.com.jeferson.password.v1.controller.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.jeferson.password.v1.controller.PasswordController;
import br.com.jeferson.password.v1.dto.PasswordRequestDto;
import br.com.jeferson.password.v1.dto.PasswordResponseDto;
import br.com.jeferson.password.v1.service.PasswordService;

public class PasswordControllerUnitTest {

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private PasswordController passwordController;

    public PasswordControllerUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should return ok when valid password")
    void shouldReturnOk_whenValidPassword() {
        PasswordRequestDto requestDto = new PasswordRequestDto();
        requestDto.setPassword("Pas$word123");

        PasswordResponseDto responseDto = new PasswordResponseDto(true, null);

        when(passwordService.validate(requestDto.password)).thenReturn(responseDto);

        ResponseEntity<PasswordResponseDto> response = passwordController.validate(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    @DisplayName("should return bad request when invalid password")
    void shouldReturnBadRequest_whenInvalidPassword() {
        PasswordRequestDto requestDto = new PasswordRequestDto();
        requestDto.setPassword("Invalid");

        PasswordResponseDto responseDto = new PasswordResponseDto(false, Arrays.asList(
                "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character."));

        when(passwordService.validate("Invalid")).thenReturn(responseDto);

        ResponseEntity<PasswordResponseDto> response = passwordController.validate(requestDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    @DisplayName("should return internal server error when unexpected error occurs")
    void shouldReturnInternalServerError_whenUnexpectedError() {
        PasswordRequestDto requestDto = new PasswordRequestDto();
        requestDto.setPassword("Invalid");

        when(passwordService.validate("Invalid")).thenThrow(new RuntimeException("unexpected error"));

        ResponseEntity<PasswordResponseDto> response = passwordController.validate(requestDto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("unexpected server error occurred", response.getBody().getErrors().get(0));
    }
}