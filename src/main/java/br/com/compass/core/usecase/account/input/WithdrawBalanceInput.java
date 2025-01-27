package br.com.compass.core.usecase.account.input;

import br.com.compass.core.domain.account.Account;

public record WithdrawBalanceInput(
        Double amount,
        Account account
) {}