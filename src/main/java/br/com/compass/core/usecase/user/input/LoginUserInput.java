package br.com.compass.core.usecase.user.input;

public record LoginUserInput(
        String cpf,
        String password
) {}