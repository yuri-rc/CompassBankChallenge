package br.com.compass.core.usecase.account.input;

import br.com.compass.core.domain.account.Account;

public record AddBalanceInput(
        Double amount,
        Account account
) {}