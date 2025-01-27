package br.com.compass.core.usecase.user.input;

import java.util.List;

public record CreateUserInput(
        String name,
        String birthDate,
        String cpf,
        String phone,
        String password,
        String confirmPassword,
        List<String> accountTypes
) {}