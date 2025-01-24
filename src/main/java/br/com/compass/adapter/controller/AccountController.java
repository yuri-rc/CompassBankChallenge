package br.com.compass.adapter.controller;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.usecase.account.GetAccountUseCase;
import br.com.compass.infra.repository.AccountRepository;

public class AccountController {
    public static Account get(Integer userId){
        GetAccountUseCase getAccountUseCase = new GetAccountUseCase(new AccountRepository());
        try {
            return getAccountUseCase.execute(userId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
        return null;
    }
}