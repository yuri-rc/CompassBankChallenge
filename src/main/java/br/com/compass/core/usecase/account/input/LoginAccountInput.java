package br.com.compass.core.usecase.account.input;

public record LoginAccountInput(
        String accountNumber,
        String password
) {}