package br.com.compass.core.usecase.account.input;

import br.com.compass.core.domain.account.Account;

public record TransfereBalanceInput(
        Double amount,
        Account account,
        String destinationAccountNumber
) {}