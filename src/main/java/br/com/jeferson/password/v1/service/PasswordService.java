package br.com.jeferson.password.v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.jeferson.password.v1.dto.PasswordResponseDto;
import br.com.jeferson.password.v1.rule.*;

@Service
public class PasswordService {

    private final List<PasswordRule> rules = new ArrayList<>();
    {
        rules.add(new MinLengthRule(9));
        rules.add(new ContainsDigitRule());
        rules.add(new ContainsLowercaseRule());
        rules.add(new ContainsUppercaseRule());
        rules.add(new ContainsSpecialCharRule("!@#$%^&*()-+"));
        rules.add(new NoRepeatedCharactersRule());
    }

    public PasswordResponseDto validate(String password) {

        List<String> errors = rules.stream()
                .filter(rule -> !rule.validate(password))
                .map(PasswordRule::message)
                .collect(Collectors.toList());

        return new PasswordResponseDto(errors.isEmpty(), errors);
    }
}
