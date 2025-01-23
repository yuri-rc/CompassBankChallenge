package br.com.compass.core.usecase.user.input;

public record CreateUserInput(
        String name,
        String birthDate,
        String cpf,
        String phone,
        String password,
        String confirmPassword
) {}