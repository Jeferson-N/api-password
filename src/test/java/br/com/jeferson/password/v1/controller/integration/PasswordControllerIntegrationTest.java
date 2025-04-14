package br.com.jeferson.password.v1.controller.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jeferson.password.v1.dto.PasswordRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("should return valid true when valid password")
	void shouldReturnValidTrue_whenValidPassword() throws Exception {

		PasswordRequestDto passwordRequestDto = new PasswordRequestDto();
		passwordRequestDto.setPassword("Pas$word123");

		String requestString = objectMapper.writeValueAsString(passwordRequestDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/password/validate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestString))
				.andExpect(MockMvcResultMatchers.status().isOk())

				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.valid").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.erros").doesNotExist());
	}

	@Test
	@DisplayName("should return valid false when invalid password")
	void shouldReturnValidFalse_whenInvalidPassword() throws Exception {

		PasswordRequestDto passwordRequestDto = new PasswordRequestDto();
		passwordRequestDto.setPassword("Invalid");

		String requestString = objectMapper.writeValueAsString(passwordRequestDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/password/validate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestString))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())

				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.valid").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(false))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]")
						.value("Password must be at least 9 characters long."))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors[1]")
						.value("Password must contain at least one digit."))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors[2]")
						.value("Password must contain at least one special character (!@#$%^&*()-+)."));
	}
}
