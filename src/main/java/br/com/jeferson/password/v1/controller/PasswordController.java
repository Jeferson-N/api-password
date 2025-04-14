package br.com.jeferson.password.v1.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import br.com.jeferson.password.v1.service.PasswordService;
import br.com.jeferson.password.v1.dto.PasswordRequestDto;
import br.com.jeferson.password.v1.dto.PasswordResponseDto;

@RestController
@RequestMapping("/api/v1/password")
@Tag(name = "Validação de Senha", description = "Endpoint para validar senhas conforme regras de negócio")
public class PasswordController {
    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/validate")
    @Operation(summary = "Valida uma senha", description = "Verifica se a senha atende às regras e retorna os erros, se houver.")
    public ResponseEntity<PasswordResponseDto> validate(@RequestBody PasswordRequestDto requestDto) {

        try {
            PasswordResponseDto response = passwordService.validate(requestDto.password);

            if (response.isValid()) {
                return ResponseEntity.ok(response);
            }

            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            String message = "unexpected server error occurred";

            logger.error(message, e);

            return ResponseEntity.status(500).<PasswordResponseDto>body(
                    new PasswordResponseDto(false, Arrays.asList(message)));
        }
    }
}
