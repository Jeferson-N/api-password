package br.com.jeferson.password.v1.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

public class PasswordResponseDto {

    private boolean valid;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(nullable = true, description = "return only when exists")
    private final List<String> errors;

    public PasswordResponseDto(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }
}
