package br.com.compass.adapter.controller;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.usecase.account.GetAccountUseCase;
import br.com.compass.core.usecase.account.LoginAccountUseCase;
import br.com.compass.core.usecase.account.input.LoginAccountInput;
import br.com.compass.infra.repository.AccountRepository;
import br.com.compass.infra.repository.UserRepository;

import java.util.Scanner;

public class AccountController {
    public static Account get(Integer userId){
        GetAccountUseCase getAccountUseCase = new GetAccountUseCase(new AccountRepository());
    public static Account login(Scanner scanner){
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account password: ");
        String password = scanner.nextLine();

        LoginAccountUseCase loginAccountUseCase = new LoginAccountUseCase(new UserRepository(), new AccountRepository());
        try {
            return loginAccountUseCase.execute(new LoginAccountInput(
                    accountNumber,
                    password
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
        return null;
    }
        try {
            return getAccountUseCase.execute(userId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
        return null;
    }
}